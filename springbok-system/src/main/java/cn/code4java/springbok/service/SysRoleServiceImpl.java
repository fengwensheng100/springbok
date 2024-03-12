package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.SysRoleDTO;
import cn.code4java.springbok.dto.SysRoleQueryDTO;
import cn.code4java.springbok.entity.SysRole;
import cn.code4java.springbok.entity.SysRoleMenu;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.mapper.SysRoleMapper;
import cn.code4java.springbok.mapper.SysRoleMenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysRoleServiceImpl
 * @Description: 角色服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private SysRoleMapper sysRoleMapper;
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 分页查询角色
     *
     * @param sysRoleQueryDTO
     * @return
     */
    @Override
    public Page<SysRole> pageSysRole(SysRoleQueryDTO sysRoleQueryDTO) {
        Page<SysRole> page = new Page<>();
        page.setCurrent(sysRoleQueryDTO.getCurrent());
        page.setSize(sysRoleQueryDTO.getSize());
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(sysRoleQueryDTO.getRoleName()), SysRole::getRoleName, sysRoleQueryDTO.getRoleName());
        queryWrapper.eq(sysRoleQueryDTO.getRoleType() != null, SysRole::getRoleType, sysRoleQueryDTO.getRoleType());
        return sysRoleMapper.selectPage(page, queryWrapper);
    }

    /**
     * 查询角色列表
     *
     * @return
     */
    @Override
    public List<SysRole> listSysRole() {
        return sysRoleMapper.selectList(new LambdaQueryWrapper<>());
    }

    /**
     * 根据id查询角色
     *
     * @param sysRoleId
     * @return
     */
    @Override
    public SysRoleDTO selectSysRoleById(int sysRoleId) {
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        SysRole sysRole = sysRoleMapper.selectById(sysRoleId);
        BeanUtils.copyProperties(sysRole, sysRoleDTO);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getSysRoleId, sysRoleId));
        if (CollectionUtil.isNotEmpty(sysRoleMenus)) {
            sysRoleDTO.setSysMenuIdList(sysRoleMenus.stream().map(SysRoleMenu::getSysMenuId).collect(Collectors.toList()));
        }
        return sysRoleDTO;
    }

    /**
     * 新增角色
     *
     * @param sysRoleDTO
     * @return
     */
    @Override
    public int addSysRole(SysRoleDTO sysRoleDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDTO, sysRole);
        sysRoleMapper.insert(sysRole);
        if (CollectionUtil.isNotEmpty(sysRoleDTO.getSysMenuIdList())) {
            sysRoleDTO.getSysMenuIdList().stream().forEach(sysMenuId -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setSysRoleId(sysRole.getSysRoleId());
                sysRoleMenu.setSysMenuId(sysMenuId);
                sysRoleMenuMapper.insert(sysRoleMenu);
            });
        }
        return 1;
    }

    /**
     * 修改角色
     *
     * @param sysRoleDTO
     * @return
     */
    @Override
    public int updateSysRole(SysRoleDTO sysRoleDTO) {
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getSysRoleId, sysRoleDTO.getSysRoleId()));
        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(sysRoleDTO, sysRole);
        sysRoleMapper.updateById(sysRole);
        if (CollectionUtil.isNotEmpty(sysRoleDTO.getSysMenuIdList())) {
            sysRoleDTO.getSysMenuIdList().stream().forEach(sysMenuId -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setSysRoleId(sysRoleDTO.getSysRoleId());
                sysRoleMenu.setSysMenuId(sysMenuId);
                sysRoleMenuMapper.insert(sysRoleMenu);
            });
        }
        return 1;
    }

    /**
     * 删除角色
     *
     * @param sysRoleId
     * @return
     */
    @Override
    public int deleteSysRole(int sysRoleId) {
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getSysRoleId, sysRoleId));
        return sysRoleId > 0 ? sysRoleMapper.deleteById(sysRoleId) : 0;
    }
}
