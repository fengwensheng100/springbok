package cn.code4java.springbok.dto.api;

import lombok.Data;

/**
 * @ClassName SaleOrderReceiptApiDTO
 * @Description: 确认收货
 * @Author fengwensheng
 * @Date 2023/11/24
 * @Version V1.0
 **/
@Data
public class SaleOrderReceiptApiDTO {
    /**
     * 订单号
     */
    private String orderNo;
}
