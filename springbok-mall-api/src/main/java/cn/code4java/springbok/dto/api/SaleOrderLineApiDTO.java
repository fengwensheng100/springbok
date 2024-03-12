package cn.code4java.springbok.dto.api;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName SaleOrderDTO
 * @Description: TODO
 * @Author fengwensheng
 * @Date 2023/11/24
 * @Version V1.0
 **/
@Data
public class SaleOrderLineApiDTO {

    /**
     * 订单id
     */
    private int saleOrderId;
    /**
     * 销售商品id
     */
    private int itemSaleId;
    /**
     * 数量
     */
    private BigDecimal quantity;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 小计
     */
    private BigDecimal amount;
    /**
     * 销售商品规格列表
     */
    private List<ItemSaleSkuApiDTO> itemSaleSkuApiDTOS;
}
