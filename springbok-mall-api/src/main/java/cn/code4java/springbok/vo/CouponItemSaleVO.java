package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.CouponItemSale;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName CouponItemSaleVO
 * @Description: CouponItemSaleVO
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
public class CouponItemSaleVO extends CouponItemSale {
    /**
     * 商品图片
     */
    private String mainImage;
    /**
     * 销售商品名称
     */
    private String itemSaleName;
    /**
     * 销售价格
     */
    private BigDecimal itemSalePrice;
}
