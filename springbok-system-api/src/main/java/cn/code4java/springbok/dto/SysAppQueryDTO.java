package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysAppQueryDTO
 * @Description: SysAppQueryDTO
 * @Author fengwensheng
 * @Date 2024/2/23
 * @Version V1.0
 **/
@Data
@Schema(title = "应用查询参数")
public class SysAppQueryDTO {

    /**
     * appId
     */
    @Schema(title = "应用id")
    private String appId;
    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
