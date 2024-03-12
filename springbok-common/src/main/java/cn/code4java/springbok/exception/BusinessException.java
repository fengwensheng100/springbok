package cn.code4java.springbok.exception;

import lombok.Getter;

/**
 * @ClassName BusinessException
 * @Description: 业务异常类
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ExceptionEnum exceptionEnum;
    private String message;

    public BusinessException(ExceptionEnum exceptionEnum, String message) {
        this.exceptionEnum = exceptionEnum;
        this.message = message;
    }
}
