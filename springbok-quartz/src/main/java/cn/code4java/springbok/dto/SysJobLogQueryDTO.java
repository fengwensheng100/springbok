package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.SysJobLog;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysJobLogQueryDTO
 * @Description: SysJobLogQueryDTO
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Data
@Schema(title = "定时任务日志查询")
public class SysJobLogQueryDTO extends SysJobLog {

    /**
     * 定时任务id
     */
    @Schema(title = "定时任务id")
    private Integer sysJobId;
    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
