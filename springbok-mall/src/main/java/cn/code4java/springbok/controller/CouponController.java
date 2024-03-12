package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.CouponDTO;
import cn.code4java.springbok.dto.CouponQueryDTO;
import cn.code4java.springbok.service.CouponService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CouponController
 * @Description: 优惠券控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "优惠券管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    /**
     * 分页查询优惠券
     *
     * @param params
     * @return
     */
    @GetMapping("/pageCoupon")
    @Operation(summary = "分页查询优惠券", description = "分页查询优惠券")
    public BaseResponse pageCoupon(CouponQueryDTO params) {
        return BaseResponse.success(couponService.pageCoupon(params));
    }

    /**
     * 根据id查询优惠券
     *
     * @param couponId
     * @return
     */
    @GetMapping("/selectCouponById")
    @Operation(summary = "根据id查询优惠券", description = "根据id查询优惠券")
    public BaseResponse selectCouponById(Integer couponId) {
        return BaseResponse.success(couponService.selectCouponById(couponId));
    }

    /**
     * 新增优惠券
     *
     * @param couponDTO
     * @return
     */
    @PostMapping("/addCoupon")
    @Operation(summary = "新增优惠券", description = "新增优惠券")
    public BaseResponse addCoupon(@RequestBody CouponDTO couponDTO) {
        return BaseResponse.success(couponService.addCoupon(couponDTO));
    }

    /**
     * 修改优惠券
     *
     * @param couponDTO
     * @return
     */
    @PostMapping("/updateCoupon")
    @Operation(summary = "修改优惠券", description = "修改优惠券")
    public BaseResponse updateCoupon(@RequestBody CouponDTO couponDTO) {
        return BaseResponse.success(couponService.updateCoupon(couponDTO));
    }

    /**
     * 删除优惠券
     *
     * @param couponId
     * @return
     */
    @PostMapping("/deleteCoupon")
    @Operation(summary = "删除优惠券", description = "删除优惠券")
    public BaseResponse deleteCoupon(Integer couponId) {
        return BaseResponse.success(couponService.deleteCoupon(couponId));
    }
}
