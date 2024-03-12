package cn.code4java.springbok.dto.api;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName SaleOrderPreNowApiDTO
 * @Description: TODO
 * @Author fengwensheng
 * @Date 2024/2/3
 * @Version V1.0
 **/
@Data
public class SaleOrderPreNowApiDTO {
    /**
     * skuId
     */
    private Integer itemSaleSkuId;
    /**
     * 数量
     */
    private BigDecimal quantity;
}
