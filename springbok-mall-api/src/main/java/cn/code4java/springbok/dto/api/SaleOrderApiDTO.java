package cn.code4java.springbok.dto.api;

import lombok.Data;

/**
 * @ClassName SaleOrderDTO
 * @Description: TODO
 * @Author fengwensheng
 * @Date 2023/11/24
 * @Version V1.0
 **/
@Data
public class SaleOrderApiDTO {
    /**
     * 预订单id
     */
    private String preOrderId;
    /**
     * 地址id
     */
    private Integer memberAddressId;
    /**
     * 店铺id
     */
    private Integer branchId;
    /**
     * 支付渠道
     * 1：微信支付
     * 2：支付宝支付
     */
    private Integer payChannel;
    /**
     * 支付方式
     * 1：在线支付
     * 2：货到付款
     */
    private Integer payType;
    /**
     * 用户备注
     */
    private String memberRemark;
}
