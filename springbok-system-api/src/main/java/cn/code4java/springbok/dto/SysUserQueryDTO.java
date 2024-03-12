package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysUserQueryDTO
 * @Description: SysUserQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
@Schema(title = "用户查询参数")
public class SysUserQueryDTO {

    /**
     * 用户名
     */
    @Schema(title = "用户名")
    private String username;
    /**
     * 手机号
     */
    @Schema(title = "手机号")
    private String mobile;
    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
