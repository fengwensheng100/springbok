package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName Coupon
 * @Description: 优惠券
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_coupon")
public class Coupon extends BaseEntity {

    /**
     * 券id
     */
    @TableId(type = IdType.AUTO)
    private Integer couponId;
    /**
     * 券名称
     */
    private String couponName;
    /**
     * 券描述
     */
    private String couponDescription;
    /**
     * 券图片
     */
    private String couponImage;
    /**
     * 券类型
     * 1：满减
     * 2：折扣
     * 场景1：满20-5
     * 场景2：全场88折
     * 场景3：烟雨乌龙买一赠一，烟雨乌龙单价19，满19-19，指定烟雨乌龙适用
     */
    private Integer couponType;
    /**
     * 券门槛
     */
    private BigDecimal couponLimit;
    /**
     * 券抵扣金额，折扣券填(0-1)区间的数字，例如0.88代表88折
     */
    private BigDecimal couponAmount;
    /**
     * 适用商品范围类型
     * 0：全部可用
     * 1：指定商品可用
     * 2：指定商品不可用
     */
    private Integer usedItemSaleType;
}
