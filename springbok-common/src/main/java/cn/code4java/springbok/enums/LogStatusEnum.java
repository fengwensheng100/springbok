package cn.code4java.springbok.enums;

/**
 * @ClassName LogStatusEnum
 * @Description: 日志状态枚举
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public enum LogStatusEnum {

    NORMAL(1, "正常"),
    ERROR(2, "错误");

    private int code;
    private String message;

    LogStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
