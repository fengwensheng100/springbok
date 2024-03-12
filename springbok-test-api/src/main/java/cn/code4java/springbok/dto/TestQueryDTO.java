package cn.code4java.springbok.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName TestQueryDTO
 * @Description: TestQueryDTO
 * @Author fengwensheng
 * @Date 2024/3/3
 * @Version V1.0
 **/
@Data
@Schema(title = "测试查询参数")
public class TestQueryDTO {

    @Schema(title = "页大小")
    private long size;
    @Schema(title = "页码")
    private long current;
}
