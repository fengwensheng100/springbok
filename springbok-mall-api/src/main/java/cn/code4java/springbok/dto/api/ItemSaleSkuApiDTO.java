package cn.code4java.springbok.dto.api;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ItemSaleSkuVO
 * @Description: TODO
 * @Author fengwensheng
 * @Date 2023/11/24
 * @Version V1.0
 **/
@Data
public class ItemSaleSkuApiDTO {

    /**
     * 规格编码
     */
    private String skuCode;
    /**
     * 库存
     */
    private BigDecimal quantity;
}
