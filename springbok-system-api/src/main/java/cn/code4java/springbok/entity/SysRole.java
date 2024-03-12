package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysRole
 * @Description: 角色表
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
@Data
@Schema(title = "角色实体")
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {

    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
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
}
