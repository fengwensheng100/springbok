package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName MemberCart
 * @Description: 购物车
 * @Author fengwensheng
 * @Date 2024/1/30
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_member_cart")
public class MemberCart {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer memberCartId;
    /**
     * 会员id
     */
    private Integer memberId;
    /**
     * 商品id
     */
    private Integer itemSaleId;
    /**
     * skuId
     */
    private Integer itemSaleSkuId;
    /**
     * 销售商品名称
     */
    private String itemSaleName;
    /**
     * 属性文字
     */
    private String attrsText;
    /**
     * 商品数量
     */
    private BigDecimal number;
    /**
     * 加入时价格
     */
    private BigDecimal addPrice;
}
