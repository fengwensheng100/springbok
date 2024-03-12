package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysJob
 * @Description: 定时任务
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Data
@Schema(title = "定时任务实体")
@TableName(value = "sys_job")
public class SysJob {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "id")
    private Integer sysJobId;
    /**
     * 任务key
     */
    @Schema(title = "任务key", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobKey;
    /**
     * 任务组
     */
    @Schema(title = "任务组", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobGroup;
    /**
     * 调度类型
     * 1：cron表达式
     */
    @Schema(title = "调度类型 1：cron表达式", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer scheduleType;
    /**
     * cron表达式
     */
    @Schema(title = "cron表达式", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cron;
    /**
     * 执行的bean
     */
    @Schema(title = "执行的bean", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bean;
    /**
     * 是否立即执行
     */
    @Schema(title = "是否立即执行", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean startNow;
    /**
     * 任务状态
     * 1：启用
     * 2：停用
     */
    @Schema(title = "任务状态 1：启用 2：停用", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;
    /**
     * 参数
     */
    @Schema(title = "参数")
    private String params;
    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remark;
}
