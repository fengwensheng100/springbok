package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysJobLog
 * @Description: 定时任务执行日志
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Data
@TableName(value = "sys_job_log")
public class SysJobLog {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "定时任务日志id")
    private Integer sysJobLogId;
    /**
     * 定时任务id
     */
    @Schema(title = "定时任务id")
    private Integer sysJobId;
    /**
     * 开始时间
     */
    @Schema(title = "开始时间")
    private Date startTime;
    /**
     * 执行状态
     * 1：成功
     * 2：失败
     */
    @Schema(title = "执行状态 1：成功 2：失败")
    private int status;
    /**
     * 错误信息
     */
    @Schema(title = "错误信息")
    private String errorMsg;
    /**
     * 执行耗时
     */
    @Schema(title = "执行耗时")
    private Long executeSpend;
}
