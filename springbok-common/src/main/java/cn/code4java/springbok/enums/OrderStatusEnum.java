package cn.code4java.springbok.enums;

/**
 * @ClassName OrderStatusEnum
 * @Description: 订单状态枚举
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public enum OrderStatusEnum {

    WAITING_PAYMENT(1, "待付款"),
    WAITING_DELIVER(2, "待发货"),
    WAITING_RECEIVED(3, "待收货"),
    WAITING_COMMENT(4, "待评价"),
    FINISH(5, "已完成"),
    CANCEL( 6, "已取消");

    private int status;
    private String desc;

    OrderStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }
}
