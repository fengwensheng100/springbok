package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName CouponItemSale
 * @Description: 优惠券商品
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_coupon_item_sale")
public class CouponItemSale {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer couponItemSaleId;
    /**
     * 券id
     */
    private Integer couponId;
    /**
     * 商品编码
     */
    private String itemCode;
}
