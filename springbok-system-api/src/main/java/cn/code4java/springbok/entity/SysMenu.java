package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.util.List;

/**
 * @ClassName SysMenu
 * @Description: 菜单
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
@Data
@Schema(title = "菜单实体")
@TableName(value = "sys_menu")
public class SysMenu {

    /**
     * 菜单id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "菜单id")
    private Integer sysMenuId;
    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String menuName;
    /**
     * 路由名称
     */
    @Schema(title = "路由名称")
    private String routerName;
    /**
     * 上级id
     */
    @Schema(title = "上级id")
    private Integer parentId;
    /**
     * 路由路径
     */
    @Schema(title = "路由路径")
    private String routerPath;
    /**
     * 路由组件路径
     */
    @Schema(title = "路由组件路径")
    private String routerComponent;
    /**
     * 菜单图标
     */
    @Schema(title = "菜单图标")
    private String menuIcon;
    /**
     * 菜单排序
     */
    @Schema(title = "菜单排序")
    private int menuSort;
    /**
     * 权限标识
     */
    @Schema(title = "权限标识")
    private String permissionCode;
    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> childrenList;
}
