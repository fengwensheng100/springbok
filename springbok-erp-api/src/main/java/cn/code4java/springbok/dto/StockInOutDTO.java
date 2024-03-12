package cn.code4java.springbok.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName StockInOutDTO
 * @Description: 库存出入库DTO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class StockInOutDTO {

    /**
     * 出入库商品列表
     */
    private List<StockInOutLine> stockInOutLines;
    /**
     * 订单类型
     */
    private Integer billType;
    /**
     * 订单号
     */
    private String orderNo;

    @Data
    public static class StockInOutLine {
        /**
         * sku code
         */
        private String skuCode;
        /**
         * 数量
         */
        private BigDecimal quantity;
    }
}
