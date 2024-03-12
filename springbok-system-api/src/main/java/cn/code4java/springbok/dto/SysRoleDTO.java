package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @ClassName SysRoleDTO
 * @Description: SysRoleDTO
 * @Author fengwensheng
 * @Date 2023/12/22
 * @Version V1.0
 **/
@Data
@Schema(title = "角色新增、修改参数")
public class SysRoleDTO {

    /**
     * 角色id
     */
    @Schema(title = "角色id")
    private Integer sysRoleId;
    /**
     * 角色名称
     */
    @Schema(title = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String roleName;
    /**
     * 角色类型
     * 1：管理员
     * 2：店长
     * 3：员工
     */
    @Schema(title = "角色类型 1：管理员 2：店长 3：员工", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer roleType;
    @Schema(title = "授权菜单列表id", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Integer> sysMenuIdList;
}
