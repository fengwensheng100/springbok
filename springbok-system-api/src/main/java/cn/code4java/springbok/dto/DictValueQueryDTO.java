package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName DictValueQueryDTO
 * @Description: DictValueQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
@Schema(title = "字典值查询参数")
public class DictValueQueryDTO {

    /**
     * 字典id
     */
    @Schema(title = "字典id")
    private Integer dictId;
    /**
     * 字典标签
     */
    @Schema(title = "字典标签")
    private String label;
    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
