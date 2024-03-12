package cn.code4java.springbok.service;

import cn.code4java.springbok.vo.UserTokenVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.dto.LoginDTO;
import cn.code4java.springbok.dto.SysUserQueryDTO;
import cn.code4java.springbok.entity.SysUser;

/**
 * @ClassName SysUserService
 * @Description: 用户服务类
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
public interface SysUserService extends IService<SysUser> {
    /**
     * 分页查询用户
     *
     * @param sysUserQueryDTO
     * @return
     */
    Page<SysUser> pageSysUser(SysUserQueryDTO sysUserQueryDTO);

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    int addSysUser(SysUser sysUser);

    /**
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    int updateSysUser(SysUser sysUser);

    /**
     * 删除用户
     *
     * @param sysUserId
     * @return
     */
    int deleteSysUser(int sysUserId);

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    UserTokenVO login(LoginDTO loginDTO);

    /**
     * 退出登录
     */
    void logout();
}
