package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysLogQueryDTO
 * @Description: SysLogQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
@Schema(title = "日志查询参数")
public class SysLogQueryDTO {

    /**
     * 日志标题
     */
    @Schema(title = "日志标题")
    private String title;
    /**
     * 日志状态
     * 1：正常
     * 2：错误
     */
    @Schema(title = "日志状态 1：正常 2：错误")
    private Integer status;
    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
