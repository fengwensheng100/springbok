package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.entity.SysMenu;

import java.util.List;

/**
 * @ClassName SysMenuService
 * @Description: 菜单服务类
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 查询菜单列表
     *
     * @param sysMenu
     * @return
     */
    List<SysMenu> listSysMenu(SysMenu sysMenu);

    /**
     * 查询用户拥有的菜单列表
     *
     * @return
     */
    List<SysMenu> listSysMenuByUser();

    /**
     * 构建菜单树
     *
     * @param sysMenu
     * @param sysMenus
     */
    void buildMenuTree(SysMenu sysMenu, List<SysMenu> sysMenus);

    /**
     * 新增菜单
     *
     * @param sysMenu
     * @return
     */
    void addSysMenu(SysMenu sysMenu);

    /**
     * 修改菜单
     *
     * @param sysMenu
     * @return
     */
    void updateSysMenu(SysMenu sysMenu);

    /**
     * 删除菜单
     *
     * @param sysMenuId
     * @return
     */
    void deleteSysMenu(int sysMenuId);

    /**
     * 刷新菜单缓存
     *
     * @return
     */
    void refreshMenuCache();
}
