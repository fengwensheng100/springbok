package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.SysJobQueryDTO;
import cn.code4java.springbok.entity.SysJob;
import org.quartz.SchedulerException;

/**
 * @ClassName SysJobService
 * @Description: 定时任务服务类
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
public interface SysJobService {
    /**
     * 分页查询定时任务
     *
     * @param sysJobQueryDTO
     * @return
     */
    Page<SysJob> pageSysJob(SysJobQueryDTO sysJobQueryDTO);

    /**
     * 根据id查询定时任务
     *
     * @param sysJobId
     * @return
     */
    SysJob selectSysJobById(Integer sysJobId);

    /**
     * 新增定时任务
     *
     * @param sysJob
     * @throws SchedulerException
     */
    void addSysJob(SysJob sysJob) throws SchedulerException;

    /**
     * 修改定时任务
     *
     * @param sysJob
     * @throws SchedulerException
     */
    void updateSysJob(SysJob sysJob) throws SchedulerException;

    /**
     * 删除定时任务
     *
     * @param sysJobId
     * @return
     * @throws SchedulerException
     */
    boolean deleteSysJob(Integer sysJobId) throws SchedulerException;

    /**
     * 暂停定时任务
     *
     * @param jobKey
     * @param jobGroup
     * @throws SchedulerException
     */
    void pauseSysJob(String jobKey, String jobGroup) throws SchedulerException;

    /**
     * 恢复定时任务
     *
     * @param jobKey
     * @param jobGroup
     * @throws SchedulerException
     */
    void resumeSysJob(String jobKey, String jobGroup) throws SchedulerException;

    /**
     * 执行一次
     *
     * @param jobKey
     * @param jobGroup
     * @throws SchedulerException
     */
    void executeOnce(String jobKey, String jobGroup) throws SchedulerException;

    /**
     * 启用/关闭定时任务
     *
     * @param sysJobId
     * @param status
     * @throws SchedulerException
     */
    void enabledSysJob(Integer sysJobId, Integer status) throws SchedulerException;
}
