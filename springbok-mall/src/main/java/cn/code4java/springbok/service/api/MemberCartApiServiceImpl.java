package cn.code4java.springbok.service.api;

import cn.code4java.springbok.entity.MemberCart;
import cn.code4java.springbok.mapper.ItemSaleMapper;
import cn.code4java.springbok.mapper.ItemSaleSkuMapper;
import cn.code4java.springbok.mapper.MemberCartMapper;
import cn.code4java.springbok.vo.MemberCartVO;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.entity.ItemSaleSku;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.utils.ListUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MemberCartApiServiceImpl
 * @Description: 购物车API服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class MemberCartApiServiceImpl extends ServiceImpl<MemberCartMapper, MemberCart> implements MemberCartApiService {

    private MemberCartMapper memberCartMapper;
    private ItemSaleMapper itemSaleMapper;
    private ItemSaleSkuMapper itemSaleSkuMapper;

    /**
     * 查询会员购物车列表
     *
     * @param memberCart
     * @return
     */
    @Override
    public List<MemberCartVO> listMemberCart(MemberCart memberCart) {
        return memberCartMapper.listMemberCart(memberCart);
    }

    /**
     * 根据多个id查询购物车
     *
     * @param ids
     * @return
     */
    @Override
    public List<MemberCartVO> listMemberCartByIds(String ids) {
        return memberCartMapper.listMemberCartByIds(ListUtils.toListAsInt(ids.split(",")));
    }

    /**
     * 新增购物车商品
     *
     * @param memberCart
     * @return
     */
    @Override
    public Integer addMemberCart(MemberCart memberCart) {
        ItemSaleSku itemSaleSku = itemSaleSkuMapper.selectById(memberCart.getItemSaleSkuId());
        if (itemSaleSku == null) {
            throw new BusinessException(ExceptionEnum.BUSINESS_DATA_ABSENT_ERROR, "商品规格不存在");
        }
        ItemSale itemSale = itemSaleMapper.selectById(itemSaleSku.getItemSaleId());
        if (itemSale == null) {
            throw new BusinessException(ExceptionEnum.BUSINESS_DATA_ABSENT_ERROR, "商品不存在");
        }
        MemberCart cart = memberCartMapper.selectOne(Wrappers.<MemberCart>lambdaQuery()
                .eq(MemberCart::getItemSaleSkuId, memberCart.getItemSaleSkuId())
                .eq(MemberCart::getMemberId, StpUtil.getLoginIdAsInt()));
        if (cart != null) {
            cart.setNumber(cart.getNumber().add(memberCart.getNumber()));
            memberCartMapper.updateById(cart);
            return cart.getMemberCartId();
        }
        memberCart.setMemberId(StpUtil.getLoginIdAsInt());
        memberCart.setItemSaleId(itemSale.getItemSaleId());
        memberCart.setItemSaleName(itemSale.getItemSaleName());
        memberCart.setAddPrice(itemSaleSku.getPrice());
        memberCart.setAttrsText(itemSaleSku.getSpec());
        memberCartMapper.insert(memberCart);
        return memberCart.getMemberCartId();
    }

    /**
     * 修改购物车商品
     *
     * @param memberCart
     * @return
     */
    @Override
    public boolean updateMemberCart(MemberCart memberCart) {
        MemberCart updateMemberCart = new MemberCart();
        updateMemberCart.setNumber(memberCart.getNumber());
        return this.update(updateMemberCart, new LambdaQueryWrapper<MemberCart>()
                .eq(MemberCart::getMemberId, StpUtil.getLoginIdAsInt())
                .eq(MemberCart::getItemSaleSkuId, memberCart.getItemSaleSkuId()));
    }

    /**
     * 删除购物车商品
     *
     * @param ids
     * @return
     */
    @Override
    public Integer deleteMemberCart(String ids) {
        return memberCartMapper.deleteBatchIds(Arrays.asList(ids.split(",")));
    }
}
