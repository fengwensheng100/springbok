package cn.code4java.springbok.exception;

/**
 * @ClassName ExceptionEnum
 * @Description: 异常枚举类
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public enum ExceptionEnum {

    SUCCESS(200, "成功"),
    ERROR(500, "错误"),
    NOT_PERMISSION(501, "没有权限"),
    NOT_LOGIN(502, "未登录"),
    PARAM_NOT_NULL_ERROR(503, "参数不能为空"),
    PARAM_ERROR(504, "参数错误"),
    DATA_ABSENT_ERROR(505, "数据不存在"),
    SIGN_ERROR(506, "签名错误"),
    BUSINESS_ORDER_STATUS_ERROR(600, "业务单据状态异常"),
    BUSINESS_PARAM_ERROR(601, "业务参数错误"),
    BUSINESS_PARAM_NOT_NULL_ERROR(602, "业务参数不能为空"),
    BUSINESS_DATA_REFERENCED_ERROR(603, "业务数据已被引用"),
    BUSINESS_DATA_ABSENT_ERROR(604, "业务数据不存在"),
    BUSINESS_DATA_EXIST_ERROR(605, "业务数据已存在"),
    BUSINESS_LOGIN_PASSWORD_ERROR(606, "用户名或密码错误");

    private int code;
    private String message;

    ExceptionEnum(int code, String message) {
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
