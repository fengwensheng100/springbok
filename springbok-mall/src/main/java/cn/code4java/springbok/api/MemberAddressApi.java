package cn.code4java.springbok.api;

import cn.code4java.springbok.service.api.MemberAddressApiService;
import cn.code4java.springbok.vo.BaseResponse;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.code4java.springbok.entity.MemberAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MemberAddressApi
 * @Description: 会员地址API
 * @Author fengwensheng
 * @Date 2024/1/18
 * @Version V1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memberAddress")
public class MemberAddressApi {

    private final MemberAddressApiService memberAddressApiService;

    /**
     * 查询会员地址列表
     *
     * @param memberAddress
     * @return
     */
    @GetMapping("/listMemberAddress")
    public BaseResponse listMemberAddress(MemberAddress memberAddress) {
        return BaseResponse.success(memberAddressApiService.list(Wrappers.lambdaQuery(memberAddress)));
    }

    /**
     * 根据id查询会员地址详情
     *
     * @param memberAddress
     * @return
     */
    @GetMapping("/selectMemberAddressById")
    public BaseResponse selectMemberAddressById(MemberAddress memberAddress) {
        return BaseResponse.success(memberAddressApiService.getById(memberAddress.getMemberAddressId()));
    }

    /**
     * 新增会员地址
     *
     * @param memberAddress
     * @return
     */
    @PostMapping("/addMemberAddress")
    public BaseResponse addMemberAddress(@RequestBody MemberAddress memberAddress) {
        return BaseResponse.success(memberAddressApiService.addMemberAddress(memberAddress));
    }

    /**
     * 修改会员地址
     *
     * @param memberAddress
     * @return
     */
    @PostMapping("/updateMemberAddress")
    public BaseResponse updateMemberAddress(@RequestBody MemberAddress memberAddress) {
        return BaseResponse.success(memberAddressApiService.updateMemberAddress(memberAddress));
    }

    /**
     * 删除会员地址
     *
     * @param memberAddress
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteMemberAddress")
    public BaseResponse deleteMemberAddress(@RequestBody MemberAddress memberAddress) throws Exception {
        return BaseResponse.success(memberAddressApiService.deleteMemberAddress(memberAddress.getMemberAddressId()));
    }
}
