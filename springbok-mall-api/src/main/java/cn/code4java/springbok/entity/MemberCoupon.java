package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName MemberCoupon
 * @Description: 会员优惠券
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_member_coupon")
public class MemberCoupon extends BaseEntity {

    /**
     * 会员优惠券id
     */
    @TableId(type = IdType.AUTO)
    private Integer memberCouponId;
    /**
     * 会员id
     */
    private Integer memberId;
    /**
     * 优惠券id
     */
    private Integer couponId;
    /**
     * 券号
     */
    private String couponNo;
    /**
     * 券状态
     * 1：未使用
     * 2：已使用
     */
    private Integer couponStatus;
}
