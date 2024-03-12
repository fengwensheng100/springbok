package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Coupon;
import cn.code4java.springbok.entity.CouponItemSale;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CouponDTO
 * @Description: CouponDTO
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
public class CouponDTO extends Coupon {

    private List<CouponItemSale> couponItemSaleList;
}
