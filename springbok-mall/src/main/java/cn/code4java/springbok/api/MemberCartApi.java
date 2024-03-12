package cn.code4java.springbok.api;

import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.entity.MemberCart;
import cn.code4java.springbok.service.api.MemberCartApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MemberCartApi
 * @Description: 购物车API
 * @Author fengwensheng
 * @Date 2024/1/18
 * @Version V1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memberCart")
public class MemberCartApi {

    private final MemberCartApiService memberCartApiService;

    /**
     * 查询购物车列表
     *
     * @param memberCart
     * @return
     */
    @GetMapping("/listMemberCart")
    public BaseResponse listMemberCart(MemberCart memberCart) {
        return BaseResponse.success(memberCartApiService.listMemberCart(memberCart));
    }

    /**
     * 新增购物车
     *
     * @param memberCart
     * @return
     */
    @PostMapping("/addMemberCart")
    public BaseResponse addMemberCart(@RequestBody MemberCart memberCart) {
        return BaseResponse.success(memberCartApiService.addMemberCart(memberCart));
    }

    /**
     * 修改购物车
     *
     * @param memberCart
     * @return
     */
    @PostMapping("/updateMemberCart")
    public BaseResponse updateMemberCart(@RequestBody MemberCart memberCart) {
        return BaseResponse.success(memberCartApiService.updateMemberCart(memberCart));
    }

    /**
     * 删除购物车
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteMemberCart")
    public BaseResponse deleteMemberCart(String ids) {
        return BaseResponse.success(memberCartApiService.deleteMemberCart(ids));
    }
}
