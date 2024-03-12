package cn.code4java.springbok.vo.api;

import cn.code4java.springbok.entity.MemberAddress;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName SaleOrderPreApiVO
 * @Description: 预订单VO
 * @Author fengwensheng
 * @Date 2024/1/31
 * @Version V1.0
 **/
@Data
public class SaleOrderPreApiVO {
    /**
     * 预订单id
     */
    private String preOrderId;
    /**
     * 商品集合
     */
    private List<ItemSaleSkuPre> itemSaleSkuPres;
    /**
     * 结算信息
     */
    private Summary summary;
    /**
     * 地址列表
     */
    private List<MemberAddress> memberAddresses;

    @Data
    public static class ItemSaleSkuPre{
        /**
         * 购物车id
         */
        private Integer memberCartId;
        /**
         * 商品id
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
         * 属性文字
         */
        private String attrsText;
        /**
         * sku图片
         */
        private String image;
        /**
         * 商品数量
         */
        private BigDecimal number;
        /**
         * 原单价
         */
        private BigDecimal price;
        /**
         * 实付单价
         */
        private BigDecimal payPrice;
        /**
         * 商品小计
         */
        private BigDecimal amount;
        /**
         * 实付商品小计
         */
        private BigDecimal payAmount;
        /**
         * 是否有效
         */
        private Boolean effective;
    }

    @Data
    public static class Summary{
        /**
         * 邮费
         */
        private BigDecimal postFee;
        /**
         * 商品总价
         */
        private BigDecimal amount;
        /**
         * 应付金额
         */
        private BigDecimal payAmount;
    }
}
