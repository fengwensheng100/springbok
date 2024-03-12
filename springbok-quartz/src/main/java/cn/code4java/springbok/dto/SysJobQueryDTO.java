package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysJobQueryDTO
 * @Description: SysJobQueryDTO
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Data
@Schema(title = "定时任务查询")
public class SysJobQueryDTO {

    /**
     * 任务key
     */
    @Schema(title = "任务key")
    private String jobKey;
    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
