package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysRoleMenu
 * @Description: 角色菜单关联表
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
@Data
@Schema(title = "角色菜单关联")
@TableName(value = "sys_role_menu")
public class SysRoleMenu {

    @TableId(type = IdType.AUTO)
    @Schema(title = "关联id")
    private Integer sysRoleMenuId;
    @Schema(title = "角色id")
    private Integer sysRoleId;
    @Schema(title = "菜单id")
    private Integer sysMenuId;
}
