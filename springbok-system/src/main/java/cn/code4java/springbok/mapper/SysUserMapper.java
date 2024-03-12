package cn.code4java.springbok.mapper;

import cn.code4java.springbok.dto.SysUserQueryDTO;
import cn.code4java.springbok.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName SysUserMapper
 * @Description: SysUserMapper
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
public interface SysUserMapper extends BaseMapper<SysUser> {
    Page<SysUser> pageSysUser(Page<SysUser> sysUserPage, @Param(value = "sysUserQueryDTO") SysUserQueryDTO sysUserQueryDTO);
}
