package cn.code4java.springbok.service.api;

import cn.code4java.springbok.entity.MemberCart;
import cn.code4java.springbok.vo.MemberCartVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName MemberCartApiService
 * @Description: 购物车API服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface MemberCartApiService extends IService<MemberCart> {
    /**
     * 查询会员购物车列表
     *
     * @param cart
     * @return
     */
    List<MemberCartVO> listMemberCart(MemberCart cart);

    /**
     * 根据多个id查询购物车
     *
     * @param ids
     * @return
     */
    List<MemberCartVO> listMemberCartByIds(String ids);

    /**
     * 新增购物车商品
     *
     * @param cart
     * @return
     */
    Integer addMemberCart(MemberCart cart);

    /**
     * 修改购物车商品
     *
     * @param cart
     * @return
     */
    boolean updateMemberCart(MemberCart cart);

    /**
     * 删除购物车商品
     *
     * @param ids
     * @return
     */
    Integer deleteMemberCart(String ids);
}
