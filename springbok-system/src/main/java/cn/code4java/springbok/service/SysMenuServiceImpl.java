package cn.code4java.springbok.service;

import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.utils.RedisUtils;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.SysMenu;
import cn.code4java.springbok.mapper.SysMenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysMenuServiceImpl
 * @Description: 菜单服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private SysMenuMapper sysMenuMapper;
    private RedisUtils redisUtils;

    /**
     * 查询菜单列表
     *
     * @param sysMenu
     * @return
     */
    @Override
    public List<SysMenu> listSysMenu(SysMenu sysMenu) {
        List<SysMenu> sysMenus = sysMenuMapper.selectList(new LambdaQueryWrapper<SysMenu>().orderByDesc(SysMenu::getMenuSort));
        List<SysMenu> rootSysMenuVOS = new LinkedList<>();
        List<SysMenu> childrenSysMenuVOS = new ArrayList<>();
        for (SysMenu menu : sysMenus) {
            if (menu.getParentId() == 0) {
                rootSysMenuVOS.add(menu);
            }
        }
        for (SysMenu menu : rootSysMenuVOS) {
            buildMenuTree(menu, sysMenus);
            childrenSysMenuVOS.add(menu);
        }
        return rootSysMenuVOS;
    }

    /**
     * 查询用户拥有的菜单列表
     *
     * @return
     */
    @Override
    public List<SysMenu> listSysMenuByUser() {
        List<SysMenu> sysMenus = sysMenuMapper.listSysMenuByUserId(StpUtil.getLoginIdAsInt());
        List<SysMenu> rootSysMenuVOS = new LinkedList<>();
        List<SysMenu> childrenSysMenuVOS = new ArrayList<>();
        for (SysMenu menu : sysMenus) {
            if (menu.getParentId() == 0) {
                rootSysMenuVOS.add(menu);
            }
        }
        for (SysMenu menu : rootSysMenuVOS) {
            this.buildMenuTree(menu, sysMenus);
            childrenSysMenuVOS.add(menu);
        }
        return rootSysMenuVOS;
    }

    /**
     * 构建菜单树
     *
     * @param sysMenu
     * @param sysMenus
     */
    @Override
    public void buildMenuTree(SysMenu sysMenu, List<SysMenu> sysMenus) {
        List<SysMenu> childrenSysMenu = sysMenus.stream().filter(m -> m.getParentId() == sysMenu.getSysMenuId()).collect(Collectors.toList());
        if (!childrenSysMenu.isEmpty()) {
            for (SysMenu children : childrenSysMenu) {
                buildMenuTree(children, sysMenus);
            }
            sysMenu.setChildrenList(childrenSysMenu);
        }
    }

    /**
     * 新增菜单
     *
     * @param sysMenu
     * @return
     */
    @Override
    public void addSysMenu(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
        refreshMenuCache();
    }

    /**
     * 修改菜单
     *
     * @param sysMenu
     * @return
     */
    @Override
    public void updateSysMenu(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
        refreshMenuCache();
    }

    /**
     * 删除菜单
     *
     * @param sysMenuId
     * @return
     */
    @Override
    public void deleteSysMenu(int sysMenuId) {
        sysMenuMapper.deleteById(sysMenuId);
        refreshMenuCache();
    }

    /**
     * 刷新菜单缓存
     *
     * @return
     */
    @Override
    public void refreshMenuCache() {
        redisUtils.setToJson(SpringbokConstant.KEY_SYSTEM_MENU, this.list());
    }
}
