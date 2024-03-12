package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysRoleQueryDTO
 * @Description: SysRoleQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
@Schema(title = "角色查询参数")
public class SysRoleQueryDTO {

    /**
     * 角色名称
     */
    @Schema(title = "角色名称")
    private String roleName;
    /**
     * 角色类型
     * 1：管理员
     * 2：店长
     * 3：员工
     */
    @Schema(title = "角色类型 1：管理员 2：店长 3：员工")
    private Integer roleType;
    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
