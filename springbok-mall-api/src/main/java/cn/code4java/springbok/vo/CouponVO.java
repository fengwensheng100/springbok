package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.Coupon;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CouponVO
 * @Description: CouponVO
 * @Author fengwensheng
 * @Date 2024/1/4
 * @Version V1.0
 **/
@Data
public class CouponVO extends Coupon {

    private List<CouponItemSaleVO> couponItemSaleList;
}
