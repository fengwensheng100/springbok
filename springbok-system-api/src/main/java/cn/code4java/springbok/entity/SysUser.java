package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysUser
 * @Description: 用户表
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
@Data
@Schema(title = "用户实体")
@TableName(value = "sys_user")
public class SysUser extends BaseEntity {

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "用户id")
    private Integer sysUserId;
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
     * 昵称
     */
    @Schema(title = "昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickName;
    /**
     * 手机号
     */
    @Schema(title = "手机号")
    private String mobile;
    /**
     * 角色id
     */
    @Schema(title = "角色id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sysRoleId;
    /**
     * 盐
     */
    @Schema(title = "盐")
    private String salt;
}
