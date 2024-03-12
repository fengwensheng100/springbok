package cn.code4java.springbok.enums;

/**
 * @ClassName PayStatusEnum
 * @Description: 支付状态枚举
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public enum PayStatusEnum {

    WAITING_PAYMENT(1, "未支付"),
    PAYMENT( 2, "已支付");

    private int status;
    private String desc;

    PayStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }
}
