package cn.code4java.springbok.exception;

import cn.code4java.springbok.vo.BaseResponse;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description: 全局异常处理器
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Object businessException(BusinessException e) {
        return BaseResponse.error(e.getExceptionEnum().getCode(), e.getMessage());
    }

    /**
     * 其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Object runtimeException(RuntimeException e) {
        e.printStackTrace();
        return BaseResponse.error(ExceptionEnum.ERROR.getCode(), ExceptionEnum.ERROR.getMessage());
    }

    @ExceptionHandler(NotPermissionException.class)
    public Object notPermissionException(NotPermissionException e) {
        return BaseResponse.error(ExceptionEnum.NOT_PERMISSION.getCode(), ExceptionEnum.NOT_PERMISSION.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public Object notLoginException(NotLoginException e) {
        return BaseResponse.error(ExceptionEnum.NOT_LOGIN.getCode(), ExceptionEnum.NOT_LOGIN.getMessage());
    }

    @ExceptionHandler(NoSuchBeanDefinitionException.class)
    public Object noSuchBeanDefinitionException(NoSuchBeanDefinitionException e) {
        return BaseResponse.error(ExceptionEnum.DATA_ABSENT_ERROR.getCode(), "bean未定义");
    }
}
