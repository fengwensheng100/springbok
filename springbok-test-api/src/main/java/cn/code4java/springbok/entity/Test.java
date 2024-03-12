package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName Test
 * @Description: Test
 * @Author fengwensheng
 * @Date 2024/3/3
 * @Version V1.0
 **/
@Data
@Schema(title = "测试实体")
@TableName(value = "test_table")
public class Test {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "id")
    private Integer testId;
    /**
     * 测试字段1
     */
    @Schema(title = "测试字段1")
    private String testCol1;

    /**
     * 测试字段2
     */
    @Schema(title = "测试字段2")
    private String testCol2;
}
