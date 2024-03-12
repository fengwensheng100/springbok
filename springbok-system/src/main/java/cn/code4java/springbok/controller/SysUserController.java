package cn.code4java.springbok.controller;

import cn.code4java.springbok.annotation.Log;
import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.dto.LoginDTO;
import cn.code4java.springbok.dto.SysUserQueryDTO;
import cn.code4java.springbok.entity.SysUser;
import cn.code4java.springbok.service.SysMenuService;
import cn.code4java.springbok.service.SysUserService;
import cn.code4java.springbok.utils.RedisUtils;
import cn.code4java.springbok.vo.BaseResponse;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SysUserController
 * @Description: 用户控制器
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
@Tag(name = "用户管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SysUserController {

    private final SysUserService sysUserService;
    private final SysMenuService sysMenuService;
    private final RedisUtils redisUtils;

    /**
     * 分页查询用户信息
     *
     * @param params
     * @return
     */
    @GetMapping("/pageSysUser")
    @Operation(summary = "分页查询用户信息", description = "分页查询用户信息")
    public BaseResponse pageSysUser(SysUserQueryDTO params) {
        return BaseResponse.success(sysUserService.pageSysUser(params));
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    @Log(title = "新增用户")
    @PostMapping("/addSysUser")
    @Operation(summary = "新增用户", description = "新增用户")
    public BaseResponse addSysUser(@RequestBody SysUser sysUser) {
        return BaseResponse.success(sysUserService.addSysUser(sysUser));
    }

    /**
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    @Log(title = "修改用户")
    @PostMapping("/updateSysUser")
    @Operation(summary = "修改用户", description = "修改用户")
    public BaseResponse updateSysUser(@RequestBody SysUser sysUser) {
        return BaseResponse.success(sysUserService.updateSysUser(sysUser));
    }

    /**
     * 删除用户
     *
     * @param sysUserId
     * @return
     */
    @Log(title = "删除用户")
    @PostMapping("/deleteSysUser")
    @Operation(summary = "删除用户", description = "删除用户")
    public BaseResponse deleteSysUser(@Parameter(description = "用户id", required = true) Integer sysUserId) {
        return BaseResponse.success(sysUserService.deleteSysUser(sysUserId));
    }

    /**
     * 用户登录接口
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录接口", description = "用户登录接口")
    public BaseResponse login(@RequestBody LoginDTO loginDTO) {
        return BaseResponse.success(sysUserService.login(loginDTO));
    }

    /**
     * 用户退出登录接口
     *
     * @return
     */
    @PostMapping("/logout")
    @Operation(summary = "用户退出登录接口", description = "用户退出登录接口")
    public BaseResponse logout() {
        sysUserService.logout();
        return BaseResponse.success();
    }

    /**
     * 查询用户拥有的菜单列表
     *
     * @return
     */
    @SaIgnore
    @GetMapping("/listSysMenuByUser")
    @Operation(summary = "查询用户拥有的菜单列表", description = "查询用户拥有的菜单列表")
    public BaseResponse listSysMenuByUser() {
        return BaseResponse.success(sysMenuService.listSysMenuByUser());
    }

    /**
     * 验证码
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @SaIgnore
    @GetMapping("/captcha")
    @Operation(summary = "验证码", description = "验证码")
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 10);
        String code = lineCaptcha.getCode();
        redisUtils.set(SpringbokConstant.KEY_LOGIN_CAPTCHA + code, code, 30, TimeUnit.SECONDS);
        lineCaptcha.write(resp.getOutputStream());
    }
}
