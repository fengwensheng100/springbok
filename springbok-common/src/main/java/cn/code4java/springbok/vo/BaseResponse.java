package cn.code4java.springbok.vo;

import cn.code4java.springbok.exception.ExceptionEnum;
import lombok.Data;

/**
 * @ClassName BaseResponse
 * @Description: 基础响应类
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
public class BaseResponse {

    /**
     * 状态码
     *
     * @see ExceptionEnum
     */
    private int code;
    /**
     * 状态消息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    public BaseResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseResponse success() {
        return new BaseResponse(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMessage(), null);
    }

    public static BaseResponse success(Object data) {
        return new BaseResponse(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMessage(), data);
    }

    public static BaseResponse error() {
        return new BaseResponse(ExceptionEnum.ERROR.getCode(), ExceptionEnum.ERROR.getMessage(), null);
    }

    public static BaseResponse error(ExceptionEnum exceptionEnum) {
        return new BaseResponse(exceptionEnum.getCode(), exceptionEnum.getMessage(), null);
    }

    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, message, null);
    }
}
