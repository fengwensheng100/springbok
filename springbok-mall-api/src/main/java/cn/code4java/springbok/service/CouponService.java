package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.CouponDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.CouponQueryDTO;
import cn.code4java.springbok.entity.Coupon;
import cn.code4java.springbok.vo.CouponVO;

/**
 * @ClassName CouponService
 * @Description: 优惠券服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface CouponService {
    /**
     * 分页查询优惠券
     *
     * @param couponQueryDTO
     * @return
     */
    Page<Coupon> pageCoupon(CouponQueryDTO couponQueryDTO);

    /**
     * 根据id查询优惠券
     *
     * @param id
     * @return
     */
    CouponVO selectCouponById(Integer id);

    /**
     * 新增优惠券
     *
     * @param couponDTO
     * @return
     */
    boolean addCoupon(CouponDTO couponDTO);

    /**
     * 修改优惠券
     *
     * @param couponDTO
     * @return
     */
    boolean updateCoupon(CouponDTO couponDTO);

    /**
     * 删除优惠券
     *
     * @param id
     * @return
     */
    boolean deleteCoupon(Integer id);
}
