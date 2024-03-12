package cn.code4java.springbok.job;

import cn.code4java.springbok.abstracts.AbstractJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * @ClassName CouponJob
 * @Description: 示例任务1
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Slf4j
@Component
public class CouponJob extends AbstractJob {

    @Override
    protected void doExecute(JobExecutionContext jobExecutionContext, String params) {
        log.info("---------------每月定时发放优惠券---------------");
    }
}
