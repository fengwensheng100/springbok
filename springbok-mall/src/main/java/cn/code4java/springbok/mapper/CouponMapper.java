package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.CouponQueryDTO;
import cn.code4java.springbok.entity.Coupon;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName CouponMapper
 * @Description: CouponMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface CouponMapper extends BaseMapper<Coupon> {
    Page<Coupon> pageCoupon(Page page, @Param(value = "query") CouponQueryDTO couponQueryDTO);
}
