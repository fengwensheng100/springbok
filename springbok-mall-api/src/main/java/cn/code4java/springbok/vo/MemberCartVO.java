package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.MemberCart;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ItemSaleVO
 * @Description: 购物车VO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class MemberCartVO extends MemberCart {

    /**
     * 库存
     */
    private BigDecimal quantity;
    /**
     * 是否有效
     */
    private Boolean effective;
    /**
     * 当前的价格
     */
    private BigDecimal price;
    /**
     * sku图片
     */
    private String image;
    /**
     * sku编码
     */
    private String skuCode;
}
