package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.SysJobLogQueryDTO;
import cn.code4java.springbok.entity.SysJobLog;

/**
 * @ClassName SysJobLogService
 * @Description: 定时任务日志服务类
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
public interface SysJobLogService {
    Page<SysJobLog> pageSysJobLog(SysJobLogQueryDTO sysJobLogQueryDTO);
}
