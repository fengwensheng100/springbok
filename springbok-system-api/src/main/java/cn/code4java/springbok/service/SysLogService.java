package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.dto.SysLogQueryDTO;
import cn.code4java.springbok.entity.SysLog;

/**
 * @ClassName SysLogService
 * @Description: 日志服务类
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
public interface SysLogService extends IService<SysLog> {
    /**
     * 分页查询日志
     *
     * @param sysLogQueryDTO
     * @return
     */
    Page<SysLog> pageSysLog(SysLogQueryDTO sysLogQueryDTO);

    /**
     * 新增日志
     *
     * @param sysLog
     * @return
     */
    int addSysLog(SysLog sysLog);
}
