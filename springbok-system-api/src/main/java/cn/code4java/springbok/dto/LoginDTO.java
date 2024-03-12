package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName LoginDTO
 * @Description: 登录参数
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
@Schema(title = "用户登录参数")
public class LoginDTO {

    /**
     * 用户名
     */
    @Schema(title = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    /**
     * 密码
     */
    @Schema(title = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userPassword;
    /**
     * 验证码
     */
    @Schema(title = "验证码")
    private String captcha;
    /**
     * 登录验证方式
     * captcha：随机验证码
     * slider：滑块验证
     * @see cn.code4java.springbok.enums.SysConfigKeyEnum.VERIFY_METHOD
     */
    @Schema(title = "登录验证方式 captcha：随机验证码 slider：滑块验证")
    private String verifyMethod = "slider";
}
