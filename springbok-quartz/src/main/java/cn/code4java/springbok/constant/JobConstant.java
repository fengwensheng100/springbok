package cn.code4java.springbok.constant;

/**
 * @ClassName JobConstants
 * @Description: 定时任务常量
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
public interface JobConstant {

    /**************** 定时任务调度类型 ****************/
    /**
     * Cron表达式
     */
    int CRON = 1;

    /**************** 定时任务启用状态 ****************/
    /**
     * 启用
     */
    int ENABLED = 1;
    /**
     * 不启用
     */
    int NOT_ENABLED = 2;

    /**************** 定时任务内置参数 ****************/
    /**
     * 自定义参数
     */
    String PARAMS = "params";
    /**
     * 定时任务id
     */
    String PARAMS_JOB_ID = "params_job_id";
}
