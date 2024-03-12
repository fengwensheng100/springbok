package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.SysLogQueryDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.SysLog;
import cn.code4java.springbok.mapper.SysLogMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysLogServiceImpl
 * @Description: 日志服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    private SysLogMapper sysLogMapper;

    /**
     * 分页查询日志
     *
     * @param sysLogQueryDTO
     * @return
     */
    @Override
    public Page<SysLog> pageSysLog(SysLogQueryDTO sysLogQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(sysLogQueryDTO.getCurrent());
        page.setSize(sysLogQueryDTO.getSize());
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(sysLogQueryDTO.getTitle()), SysLog::getTitle, sysLogQueryDTO.getTitle());
        queryWrapper.eq(sysLogQueryDTO.getStatus() != null, SysLog::getStatus, sysLogQueryDTO.getStatus());
        return sysLogMapper.selectPage(page, queryWrapper);
    }

    /**
     * 新增日志
     *
     * @param sysLog
     * @return
     */
    @Override
    public int addSysLog(SysLog sysLog) {
        return sysLogMapper.insert(sysLog);
    }
}
