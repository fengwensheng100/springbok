package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Coupon;
import lombok.Data;

/**
 * @ClassName CouponQueryDTO
 * @Description: CouponQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class CouponQueryDTO extends Coupon {

    private long size;
    private long current;
}
