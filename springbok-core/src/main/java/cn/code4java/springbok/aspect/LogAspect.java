package cn.code4java.springbok.aspect;

import cn.code4java.springbok.annotation.Log;
import cn.code4java.springbok.entity.SysLog;
import cn.code4java.springbok.enums.LogStatusEnum;
import cn.code4java.springbok.service.SysLogService;
import cn.hutool.extra.servlet.ServletUtil;
import cn.code4java.springbok.utils.ServletUtils;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName LogAspect
 * @Description: 日志切面
 * @Author fengwensheng
 * @Date 2024/1/3
 * @Version V1.0
 **/
@Aspect
@Component
@AllArgsConstructor
public class LogAspect {

    private SysLogService sysLogService;

    private static ThreadLocal<Long> requestSpendThreadLocal = new ThreadLocal<>();

    @Pointcut("@annotation(cn.code4java.springbok.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理前执行
     *
     * @param joinPoint
     */
    @Before(value = "logPointCut()")
    public void Before(JoinPoint joinPoint) {
        requestSpendThreadLocal.set(System.currentTimeMillis());
    }

    /**
     * 处理后执行
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "methodResult")
    public void doAfterReturning(JoinPoint joinPoint, Object methodResult) {
        handleAnnotation(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleAnnotation(joinPoint, e);
    }

    private void handleAnnotation(JoinPoint joinPoint, Exception e) {
        Log log = getAnnotation(joinPoint);
        if (log == null) {
            return;
        }
        SysLog sysLog = new SysLog();
        sysLog.setRequestTime(new Date());
        sysLog.setStatus(LogStatusEnum.NORMAL.getCode());
        if (e != null) {
            sysLog.setStatus(LogStatusEnum.ERROR.getCode());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            sysLog.setErrorMsg(baos.toString());
        }
        // 日志标题
        sysLog.setTitle(log.title());
        // 请求地址
        sysLog.setRequestUrl(ServletUtils.getRequest().getRequestURI());
        // 请求参数
        String body = ServletUtils.getBody();
        sysLog.setRequestParam(body);
        // 请求耗时
        sysLog.setRequestSpend(System.currentTimeMillis() - requestSpendThreadLocal.get());
        // 请求IP
        sysLog.setRequestIp(ServletUtil.getClientIP(ServletUtils.getRequest()));
        sysLogService.addSysLog(sysLog);
    }

    private Log getAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
