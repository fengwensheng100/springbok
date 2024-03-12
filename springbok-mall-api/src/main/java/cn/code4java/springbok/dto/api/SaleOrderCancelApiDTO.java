package cn.code4java.springbok.dto.api;

import lombok.Data;

/**
 * @ClassName SaleOrderCancelApiDTO
 * @Description: 取消订单
 * @Author fengwensheng
 * @Date 2023/11/24
 * @Version V1.0
 **/
@Data
public class SaleOrderCancelApiDTO {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 取消原因
     */
    private String cancelReason;
}
