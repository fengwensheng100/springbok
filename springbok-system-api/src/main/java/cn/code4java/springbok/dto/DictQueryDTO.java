package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName DictQueryDTO
 * @Description: DictQueryDTO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
@Schema(title = "字典查询参数")
public class DictQueryDTO {

    @Schema(title = "字典名称")
    private String dictName;
    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
