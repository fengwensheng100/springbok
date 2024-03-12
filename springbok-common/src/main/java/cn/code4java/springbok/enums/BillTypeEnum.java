package cn.code4java.springbok.enums;

/**
 * @ClassName BillTypeEnum
 * @Description: 订单类型枚举
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public enum BillTypeEnum {

    SALE_OUT_ORDER(1, "销售出库单"),
    SALE_IN_ORDER(2, "销售退货单"),
    PURCHASE_IN_ORDER(3, "采购入库单"),
    PURCHASE_OUT_ORDER(4, "采购出库单"),
    INVENTORY_ORDER(5, "盘点单");

    private int code;
    private String message;

    BillTypeEnum(int code, String message) {
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
