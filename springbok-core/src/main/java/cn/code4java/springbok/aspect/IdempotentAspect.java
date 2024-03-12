package cn.code4java.springbok.aspect;

import cn.code4java.springbok.annotation.Idempotent;
import cn.code4java.springbok.properties.ApiProperties;
import cn.hutool.crypto.digest.MD5;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.utils.RedisUtils;
import cn.code4java.springbok.utils.ServletUtils;
import cn.code4java.springbok.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ApiSecurityAspect
 * @Description: Api安全切面
 * @Author fengwensheng
 * @Date 2024/2/23
 * @Version V1.0
 **/
@Aspect
@Component
@AllArgsConstructor
public class IdempotentAspect {

    private RedisUtils redisUtils;
    private ApiProperties apiProperties;
    private static ThreadLocal<String> idempotentKeyThreadLocal = new ThreadLocal<>();

    @Pointcut("@annotation(cn.code4java.springbok.annotation.Idempotent)")
    public void logPointCut() {
    }

    /**
     * 处理前执行
     *
     * @param joinPoint
     */
    @Before(value = "logPointCut()")
    public void Before(JoinPoint joinPoint) {
        handleAnnotation(joinPoint, null);
    }

    /**
     * 处理后执行
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "methodResult")
    public void doAfterReturning(JoinPoint joinPoint, Object methodResult) {
        // 删除key
        String key = idempotentKeyThreadLocal.get();
        if (key == null) {
            return;
        }
        redisUtils.delete(key);
    }

    private void handleAnnotation(JoinPoint joinPoint, Exception e) {
        Idempotent annotation = getAnnotation(joinPoint);
        if (annotation == null) {
            return;
        }
        String key = getIdempotentKey();
        if (key == null) {
            return;
        }
        if (redisUtils.hasKey(key)) {
            throw new BusinessException(ExceptionEnum.PARAM_ERROR, annotation.message());
        }
        if (annotation.expireTime() > 0) {
            redisUtils.set(key, "1", annotation.expireTime(), TimeUnit.SECONDS);
        } else {
            redisUtils.set(key, "1");
        }
    }

    private Idempotent getAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Idempotent.class);
        }
        return null;
    }

    private String getIdempotentKey(){
        String requestURI = ServletUtils.getRequest().getRequestURI();
        if (requestURI.substring(0,1).equals("/")){
            requestURI = requestURI.substring(1);
        }
        requestURI = requestURI.replaceAll("/", ".");
        // 请求体参数和url后携带的参数两者必须有一个不为空，若不携带参数则幂等注解不生效
        String body = ServletUtils.getBody();
        String queryString = ServletUtils.getRequest().getQueryString();
        if (StringUtils.isBlank(body) && StringUtils.isBlank(queryString)) {
            return null;
        }
        String params = (StringUtils.isNotBlank(body) ? body : "")
                + (StringUtils.isNotBlank(queryString) ? queryString : "");
        String key = apiProperties.getIdempotent().getKeyPrefix() + ":" + requestURI + ":" + MD5.create().digestHex(params);
        idempotentKeyThreadLocal.set(key);
        return key;
    }
}
