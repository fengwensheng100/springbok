package cn.code4java.springbok.service.impl;

import cn.code4java.springbok.service.SysJobLogService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.dto.SysJobLogQueryDTO;
import cn.code4java.springbok.entity.SysJobLog;
import cn.code4java.springbok.mapper.SysJobLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysJobLogServiceImpl
 * @Description: 定时任务日志服务实现类
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Slf4j
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {

    @Override
    public Page<SysJobLog> pageSysJobLog(SysJobLogQueryDTO sysJobLogQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(sysJobLogQueryDTO.getCurrent());
        page.setSize(sysJobLogQueryDTO.getSize());
        return this.page(page, Wrappers.<SysJobLog>lambdaQuery().eq(SysJobLog::getSysJobId, sysJobLogQueryDTO.getSysJobId()));
    }
}
