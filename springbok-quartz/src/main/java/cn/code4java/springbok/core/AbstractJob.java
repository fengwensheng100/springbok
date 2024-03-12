package cn.code4java.springbok.core;

import cn.code4java.springbok.constant.JobConstant;
import cn.code4java.springbok.entity.SysJobLog;
import cn.code4java.springbok.enums.LogStatusEnum;
import cn.code4java.springbok.mapper.SysJobLogMapper;
import cn.hutool.extra.spring.SpringUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * @ClassName AbstractJob
 * @Description: AbstractJob
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
public abstract class AbstractJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Exception exception = null;
        Long startTime = System.currentTimeMillis();
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        try {
            this.doExecute(jobExecutionContext, jobDataMap.getString(JobConstant.PARAMS));
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            // 执行日志
            this.log(jobDataMap.getInt(JobConstant.PARAMS_JOB_ID), exception, System.currentTimeMillis() - startTime);
        }
    }

    protected abstract void doExecute(JobExecutionContext jobExecutionContext, String params);

    private void log(Integer sysJobId, Exception e, Long executeSpend){
        SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setStatus(LogStatusEnum.NORMAL.getCode());
        if (e != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            sysJobLog.setErrorMsg(baos.toString());
            sysJobLog.setStatus(LogStatusEnum.ERROR.getCode());
        }
        sysJobLog.setSysJobId(sysJobId);
        sysJobLog.setExecuteSpend(executeSpend);
        sysJobLog.setStartTime(new Date());
        SysJobLogMapper sysJobLogMapper = SpringUtil.getBean(SysJobLogMapper.class);
        sysJobLogMapper.insert(sysJobLog);
    }
}
