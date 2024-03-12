package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.CouponDTO;
import cn.code4java.springbok.dto.CouponQueryDTO;
import cn.code4java.springbok.entity.Coupon;
import cn.code4java.springbok.entity.CouponItemSale;
import cn.code4java.springbok.mapper.CouponItemSaleMapper;
import cn.code4java.springbok.mapper.CouponMapper;
import cn.code4java.springbok.vo.CouponItemSaleVO;
import cn.code4java.springbok.vo.CouponVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CouponServiceImpl
 * @Description: 优惠券服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    private CouponMapper couponMapper;
    private CouponItemSaleMapper couponItemSaleMapper;

    /**
     * 分页查询优惠券
     *
     * @param couponQueryDTO
     * @return
     */
    @Override
    public Page<Coupon> pageCoupon(CouponQueryDTO couponQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(couponQueryDTO.getCurrent());
        page.setSize(couponQueryDTO.getSize());
        return couponMapper.pageCoupon(page, couponQueryDTO);
    }

    /**
     * 根据id查询优惠券
     *
     * @param id
     * @return
     */
    @Override
    public CouponVO selectCouponById(Integer id) {
        Coupon coupon = couponMapper.selectById(id);
        CouponVO couponVO = BeanUtil.toBean(coupon, CouponVO.class);
        List<CouponItemSaleVO> couponItemSaleSkuVOS = couponItemSaleMapper.selectCouponItemSale(id);
        couponVO.setCouponItemSaleList(couponItemSaleSkuVOS);
        return couponVO;
    }

    /**
     * 新增优惠券
     *
     * @param couponDTO
     * @return
     */
    @Override
    @Transactional
    public boolean addCoupon(CouponDTO couponDTO) {
        save(couponDTO);
        if (CollectionUtil.isNotEmpty(couponDTO.getCouponItemSaleList())) {
            couponDTO.getCouponItemSaleList().stream().forEach(couponItemSaleSku -> {
                couponItemSaleSku.setCouponId(couponDTO.getCouponId());
                couponItemSaleMapper.insert(couponItemSaleSku);
            });
        }
        return true;
    }

    /**
     * 修改优惠券
     *
     * @param couponDTO
     * @return
     */
    @Override
    @Transactional
    public boolean updateCoupon(CouponDTO couponDTO) {
        couponItemSaleMapper.delete(Wrappers.<CouponItemSale>lambdaQuery().eq(CouponItemSale::getCouponId, couponDTO.getCouponId()));
        if (CollectionUtil.isNotEmpty(couponDTO.getCouponItemSaleList())) {
            couponDTO.getCouponItemSaleList().stream().forEach(couponItemSaleSku -> {
                couponItemSaleSku.setCouponId(couponDTO.getCouponId());
                couponItemSaleMapper.insert(couponItemSaleSku);
            });
        }
        return updateById(couponDTO);
    }

    /**
     * 删除优惠券
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean deleteCoupon(Integer id) {
        couponItemSaleMapper.delete(Wrappers.<CouponItemSale>lambdaQuery().eq(CouponItemSale::getCouponId, id));
        return removeById(id);
    }
}
