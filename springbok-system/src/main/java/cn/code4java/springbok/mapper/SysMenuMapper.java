package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.code4java.springbok.entity.SysMenu;

import java.util.List;

/**
 * @ClassName SysMenuMapper
 * @Description: SysMenuMapper
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> listSysMenuByUserId(int sysUserId);
}
