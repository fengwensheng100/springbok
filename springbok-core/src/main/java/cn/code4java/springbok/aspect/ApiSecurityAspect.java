package cn.code4java.springbok.aspect;

import cn.code4java.springbok.annotation.ApiSecurity;
import cn.code4java.springbok.annotation.Idempotent;
import cn.code4java.springbok.entity.SysApp;
import cn.code4java.springbok.service.SysAppService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.properties.ApiProperties;
import cn.code4java.springbok.utils.ServletUtils;
import cn.code4java.springbok.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

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
public class ApiSecurityAspect {

    private SysAppService sysAppService;
    private ApiProperties apiProperties;

    @Pointcut("@within(cn.code4java.springbok.annotation.ApiSecurity) || @annotation(cn.code4java.springbok.annotation.ApiSecurity)")
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

    private void handleAnnotation(JoinPoint joinPoint, Exception e) {
        ApiSecurity annotation = getAnnotation(joinPoint);
        String token = ServletUtils.getRequest().getHeader("Springbok-Token");
        if (annotation.checkLogin()) {
            if (StringUtils.isBlank(token)) {
                throw new BusinessException(ExceptionEnum.PARAM_NOT_NULL_ERROR, "token不能为空");
            }
            StpUtil.checkLogin();
        }
        String timestamp = ServletUtils.getRequest().getHeader("timestamp");
        if (StringUtils.isBlank(timestamp)) {
            throw new BusinessException(ExceptionEnum.PARAM_NOT_NULL_ERROR, "timestamp参数不能为空");
        }
        String appId = ServletUtils.getRequest().getHeader("appId");
        if (StringUtils.isBlank(appId)) {
            throw new BusinessException(ExceptionEnum.PARAM_NOT_NULL_ERROR, "appId参数不能为空");
        }
        String sign = ServletUtils.getRequest().getHeader("sign");
        if (StringUtils.isBlank(sign)) {
            throw new BusinessException(ExceptionEnum.PARAM_NOT_NULL_ERROR, "sign参数不能为空");
        }
        // 校验是否过期
        if (System.currentTimeMillis() - (apiProperties.getSecurity().getExpireTime() * 1000) > Long.valueOf(timestamp)) {
            throw new BusinessException(ExceptionEnum.PARAM_ERROR, "请求已过期");
        }
        // 校验签名，防止篡改，校验规则：sign = MD5(参数升序&timestamp=xxx&appSecret=xxx)，拼接在url后的参数不参与签名
        SysApp sysApp = sysAppService.getOne(Wrappers.<SysApp>lambdaQuery().eq(SysApp::getAppId, appId));
        if (sysApp == null) {
            throw new BusinessException(ExceptionEnum.PARAM_ERROR, "应用不存在");
        }
        StringBuffer stringBuffer = new StringBuffer();
        String body = ServletUtils.getBody();
        if (StringUtils.isNotBlank(body)) {
            Map<String, Object> paramsMap = JSONUtil.toBean(body, HashMap.class);
            List<String> list = new ArrayList<>(paramsMap.keySet());
            Collections.sort(list);
            for (String key : list) {
                stringBuffer.append(key + "=" + paramsMap.get(key) + "&");
            }
        }
        stringBuffer.append("timestamp=" + timestamp);
        stringBuffer.append("&");
        stringBuffer.append("appSecret=" + sysApp.getAppSecret());
        String signStr = MD5.create().digestHex(stringBuffer.toString());
        if (!signStr.equals(sign)) {
            throw new BusinessException(ExceptionEnum.SIGN_ERROR, ExceptionEnum.SIGN_ERROR.getMessage());
        }
    }

    private ApiSecurity getAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Annotation[] annotations = signature.getDeclaringType().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ApiSecurity) {
                return (ApiSecurity) annotation;
            }
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(ApiSecurity.class);
        }
        return null;
    }
}
