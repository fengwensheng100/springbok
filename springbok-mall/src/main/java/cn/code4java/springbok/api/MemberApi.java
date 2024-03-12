package cn.code4java.springbok.api;

import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.entity.Member;
import cn.code4java.springbok.service.api.MemberApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName MemberApi
 * @Description: 会员API
 * @Author fengwensheng
 * @Date 2024/1/18
 * @Version V1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApi {

    private final MemberApiService memberApiService;

    /**
     * 微信手机号登录
     *
     * @param member
     * @return
     */
    @PostMapping("/wxLogin")
    public BaseResponse wxLogin(@RequestBody Member member) {
        return BaseResponse.success(memberApiService.wxLogin(member.getPhone()));
    }

    /**
     * 查询会员信息
     *
     * @return
     */
    @GetMapping("/selectMember")
    public BaseResponse selectMember() {
        return BaseResponse.success(memberApiService.selectMember());
    }

    /**
     * 修改会员信息
     *
     * @param member
     * @return
     */
    @PostMapping("/updateMember")
    public BaseResponse updateMember(@RequestBody Member member) {
        return BaseResponse.success(memberApiService.updateMember(member));
    }

    /**
     * 上传头像
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadAvatar")
    public BaseResponse uploadAvatar(@RequestParam("file") MultipartFile file) throws Exception {
        return BaseResponse.success(memberApiService.uploadAvatar(file));
    }
}
