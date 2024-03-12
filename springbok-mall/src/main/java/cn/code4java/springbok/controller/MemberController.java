package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.MemberQueryDTO;
import cn.code4java.springbok.service.MemberService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberController
 * @Description: 会员控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "会员管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 分页查询会员信息
     *
     * @param params
     * @return
     */
    @GetMapping("/pageMember")
    @Operation(summary = "分页查询会员信息", description = "分页查询会员信息")
    public BaseResponse pageMember(MemberQueryDTO params) {
        return BaseResponse.success(memberService.pageMember(params));
    }

    /**
     * 根据id查询会员信息
     *
     * @param id
     * @return
     */
    @GetMapping("/selectMemberById")
    @Operation(summary = "根据id查询会员信息", description = "根据id查询会员信息")
    public BaseResponse selectMemberById(Integer id) {
        return BaseResponse.success(memberService.selectMemberById(id));
    }
}
