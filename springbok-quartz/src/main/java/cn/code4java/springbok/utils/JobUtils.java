package cn.code4java.springbok.utils;

import cn.code4java.springbok.constant.JobConstant;
import cn.code4java.springbok.entity.SysJob;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.quartz.*;

/**
 * @ClassName JobUtils
 * @Description: JobUtils
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
public class JobUtils {

    /**
     * 创建定时任务
     * @param scheduler
     * @param sysJob
     * @throws SchedulerException
     */
    public static void createJob(Scheduler scheduler, SysJob sysJob) throws SchedulerException {
        Class jobClass = ClassUtil.getClass(SpringUtil.getBean(sysJob.getBean()));
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .usingJobData(JobConstant.PARAMS, sysJob.getParams())
                .usingJobData(JobConstant.PARAMS_JOB_ID, sysJob.getSysJobId())
                .withIdentity(JobKey.jobKey(sysJob.getJobKey(), sysJob.getJobGroup()))
                .build();
        TriggerBuilder triggerBuilder;
        switch (sysJob.getScheduleType()) {
            case JobConstant.CRON:
                triggerBuilder = TriggerBuilder.newTrigger()
                        .withIdentity(sysJob.getJobKey(), sysJob.getJobGroup())
                        .withSchedule(CronScheduleBuilder.cronSchedule(sysJob.getCron()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sysJob.getScheduleType());
        }
        if (sysJob.isStartNow()) {
            triggerBuilder.startNow();
        }
        scheduler.scheduleJob(jobDetail, triggerBuilder.build());
        if (sysJob.getStatus() != JobConstant.ENABLED) {
            scheduler.pauseJob(jobDetail.getKey());
        }
    }
}
