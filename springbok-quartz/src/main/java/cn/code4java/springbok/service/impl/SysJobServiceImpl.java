package cn.code4java.springbok.service.impl;

import cn.code4java.springbok.mapper.SysJobMapper;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.cron.CronException;
import cn.hutool.cron.pattern.CronPattern;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.utils.JobUtils;
import cn.code4java.springbok.constant.JobConstant;
import cn.code4java.springbok.dto.SysJobQueryDTO;
import cn.code4java.springbok.entity.SysJob;
import cn.code4java.springbok.entity.SysJobLog;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.SysJobLogMapper;
import cn.code4java.springbok.service.SysJobService;
import cn.code4java.springbok.utils.StringUtils;
import cn.code4java.springbok.vo.SysJobVO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysJobServiceImpl
 * @Description: 定时任务服务实现类
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Slf4j
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

    @Resource
    private Scheduler scheduler;
    @Resource
    private SysJobLogMapper sysJobLogMapper;

    /**
     * 初始化定时任务
     *
     * @throws SchedulerException
     */
    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.clear();
        List<SysJob> sysJobs = this.list();
        if (CollectionUtil.isNotEmpty(sysJobs)) {
            for (SysJob sysJob : sysJobs) {
                JobUtils.createJob(scheduler, sysJob);
            }
        }
    }

    /**
     * 分页查询定时任务
     *
     * @param sysJobQueryDTO
     * @return
     */
    @Override
    public Page<SysJob> pageSysJob(SysJobQueryDTO sysJobQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(sysJobQueryDTO.getCurrent());
        page.setSize(sysJobQueryDTO.getSize());
        LambdaQueryWrapper wrappers = Wrappers.<SysJob>lambdaQuery()
                .like(StringUtils.isNotBlank(sysJobQueryDTO.getJobKey()), SysJob::getJobKey, sysJobQueryDTO.getJobKey());
        return this.page(page, wrappers);
    }

    /**
     * 根据id查询定时任务
     *
     * @param sysJobId
     * @return
     */
    @Override
    public SysJob selectSysJobById(Integer sysJobId) {
        SysJob sysJob = this.getById(sysJobId);
        SysJobVO sysJobVO = new SysJobVO();
        BeanUtils.copyProperties(sysJob, sysJobVO);
        List<SysJobLog> sysJobLogs = sysJobLogMapper.selectList(Wrappers.<SysJobLog>lambdaQuery().eq(SysJobLog::getSysJobId, sysJobId));
        sysJobVO.setSysJobLogs(sysJobLogs);
        return sysJobVO;
    }

    /**
     * 新增定时任务
     *
     * @param sysJob
     * @throws SchedulerException
     */
    @Override
    @Transactional
    public void addSysJob(SysJob sysJob) throws SchedulerException {
        if (StringUtils.isBlank(sysJob.getJobKey())) {
            throw new BusinessException(ExceptionEnum.PARAM_NOT_NULL_ERROR, "jobKey不能为空");
        }
        try {
            CronPattern.of(sysJob.getCron());
        } catch (CronException e) {
            throw new BusinessException(ExceptionEnum.PARAM_ERROR, "cron表达式格式错误");
        }
        this.save(sysJob);
        JobUtils.createJob(scheduler, sysJob);
    }

    /**
     * 修改定时任务
     *
     * @param sysJob
     * @throws SchedulerException
     */
    @Override
    @Transactional
    public void updateSysJob(SysJob sysJob) throws SchedulerException {
        if (StringUtils.isBlank(sysJob.getJobKey())) {
            throw new BusinessException(ExceptionEnum.PARAM_NOT_NULL_ERROR, "jobKey不能为空");
        }
        JobKey key = StringUtils.isBlank(sysJob.getJobGroup()) ? JobKey.jobKey(sysJob.getJobKey()) : JobKey.jobKey(sysJob.getJobKey(), sysJob.getJobGroup());
        if (scheduler.checkExists(key)) {
            scheduler.deleteJob(key);
        }
        this.updateById(sysJob);
        JobUtils.createJob(scheduler, sysJob);
    }

    /**
     * 删除定时任务
     *
     * @param sysJobId
     * @return
     * @throws SchedulerException
     */
    @Override
    public boolean deleteSysJob(Integer sysJobId) throws SchedulerException {
        SysJob sysJob = this.getById(sysJobId);
        if (sysJob == null) {
            throw new BusinessException(ExceptionEnum.DATA_ABSENT_ERROR, ExceptionEnum.DATA_ABSENT_ERROR.getMessage());
        }
        JobKey key = StringUtils.isBlank(sysJob.getJobGroup()) ? JobKey.jobKey(sysJob.getJobKey()) : JobKey.jobKey(sysJob.getJobKey(), sysJob.getJobGroup());
        scheduler.deleteJob(key);
        return this.removeById(sysJobId);
    }

    /**
     * 暂停定时任务
     *
     * @param jobKey
     * @param jobGroup
     * @throws SchedulerException
     */
    @Override
    public void pauseSysJob(String jobKey, String jobGroup) throws SchedulerException {
        if (StringUtils.isBlank(jobKey)) {
            throw new BusinessException(ExceptionEnum.PARAM_NOT_NULL_ERROR, "jobKey不能为空");
        }
        JobKey key = StringUtils.isBlank(jobGroup) ? JobKey.jobKey(jobKey) : JobKey.jobKey(jobKey, jobGroup);
        scheduler.pauseJob(key);
    }

    /**
     * 恢复定时任务
     *
     * @param jobKey
     * @param jobGroup
     * @throws SchedulerException
     */
    @Override
    public void resumeSysJob(String jobKey, String jobGroup) throws SchedulerException {
        if (StringUtils.isBlank(jobKey)) {
            throw new BusinessException(ExceptionEnum.PARAM_NOT_NULL_ERROR, "jobKey不能为空");
        }
        JobKey key = StringUtils.isBlank(jobGroup) ? JobKey.jobKey(jobKey) : JobKey.jobKey(jobKey, jobGroup);
        scheduler.resumeJob(key);
    }

    /**
     * 执行一次
     *
     * @param jobKey
     * @param jobGroup
     * @throws SchedulerException
     */
    @Override
    public void executeOnce(String jobKey, String jobGroup) throws SchedulerException {
        JobKey key = StringUtils.isBlank(jobGroup) ? JobKey.jobKey(jobKey) : JobKey.jobKey(jobKey, jobGroup);
        JobDetail jobDetail = scheduler.getJobDetail(key);
        scheduler.triggerJob(key, jobDetail.getJobDataMap());
    }

    /**
     * 启用/关闭定时任务
     *
     * @param sysJobId
     * @param status
     * @throws SchedulerException
     */
    @Override
    @Transactional
    public void enabledSysJob(Integer sysJobId, Integer status) throws SchedulerException {
        SysJob sysJob = this.getById(sysJobId);
        if (sysJob == null) {
            throw new BusinessException(ExceptionEnum.DATA_ABSENT_ERROR, "定时任务不存在");
        }
        sysJob.setStatus(status);
        this.updateById(sysJob);
        JobKey key = StringUtils.isBlank(sysJob.getJobGroup()) ? JobKey.jobKey(sysJob.getJobKey()) : JobKey.jobKey(sysJob.getJobKey(), sysJob.getJobGroup());
        if (status == JobConstant.ENABLED) {
            scheduler.resumeJob(key);
        } else if (status == JobConstant.NOT_ENABLED) {
            scheduler.pauseJob(key);
        }
    }
}
