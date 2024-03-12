package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.code4java.springbok.entity.CouponItemSale;
import cn.code4java.springbok.vo.CouponItemSaleVO;

import java.util.List;

/**
 * @ClassName CouponItemSaleMapper
 * @Description: CouponItemSaleMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface CouponItemSaleMapper extends BaseMapper<CouponItemSale> {
    List<CouponItemSaleVO> selectCouponItemSale(int couponId);
}
