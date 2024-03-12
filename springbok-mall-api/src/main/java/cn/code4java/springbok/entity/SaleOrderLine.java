package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName SaleOrderLine
 * @Description: 销售订单明细
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_sale_order_line")
public class SaleOrderLine {

    /**
     * 订单明细id
     */
    @TableId(type = IdType.AUTO)
    private Integer saleOrderLineId;
    /**
     * 订单id
     */
    private Integer saleOrderId;
    /**
     * 销售商品id
     */
    private Integer itemSaleId;
    /**
     * skuId
     */
    private Integer itemSaleSkuId;
    /**
     * sku编码
     */
    private String skuCode;
    /**
     * 销售商品名称
     */
    private String itemSaleName;
    /**
     * 规格字符串
     */
    private String attrsText;
    /**
     * sku商品图片
     */
    private String image;
    /**
     * 数量
     */
    private BigDecimal quantity;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 实付单价
     */
    private BigDecimal payPrice;
    /**
     * 小计
     */
    private BigDecimal amount;
    /**
     * 实付小计
     */
    private BigDecimal payAmount;
}
