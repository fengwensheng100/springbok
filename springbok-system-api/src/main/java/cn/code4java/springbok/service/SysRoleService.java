package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.dto.SysRoleDTO;
import cn.code4java.springbok.dto.SysRoleQueryDTO;
import cn.code4java.springbok.entity.SysRole;

import java.util.List;

/**
 * @ClassName SysRoleService
 * @Description: 角色服务类
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
public interface SysRoleService extends IService<SysRole> {
    /**
     * 分页查询角色
     *
     * @param sysRoleQueryDTO
     * @return
     */
    Page<SysRole> pageSysRole(SysRoleQueryDTO sysRoleQueryDTO);

    /**
     * 查询角色列表
     *
     * @return
     */
    List<SysRole> listSysRole();

    /**
     * 根据id查询角色
     *
     * @param sysRoleId
     * @return
     */
    SysRoleDTO selectSysRoleById(int sysRoleId);

    /**
     * 新增角色
     *
     * @param sysRoleDTO
     * @return
     */
    int addSysRole(SysRoleDTO sysRoleDTO);

    /**
     * 修改角色
     *
     * @param sysRoleDTO
     * @return
     */
    int updateSysRole(SysRoleDTO sysRoleDTO);

    /**
     * 删除角色
     *
     * @param sysRoleId
     * @return
     */
    int deleteSysRole(int sysRoleId);
}
