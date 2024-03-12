package cn.code4java.springbok.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ItemSaleStatisticsVO
 * @Description: ItemSaleStatisticsVO
 * @Author fengwensheng
 * @Date 2024/2/20
 * @Version V1.0
 **/
@Data
public class ItemSaleStatisticsVO {
    /**
     * 商品名称
     */
    private String itemSaleName;
    /**
     * 销售额
     */
    private BigDecimal amount;
}
