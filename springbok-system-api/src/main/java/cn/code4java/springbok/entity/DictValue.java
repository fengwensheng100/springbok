package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName DictValue
 * @Description: 字典值
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@Schema(title = "字典值实体")
@TableName(value = "sys_dict_value")
public class DictValue extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "id")
    private Integer dictValueId;
    /**
     * 字典id
     */
    @Schema(title = "字典id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer dictId;
    /**
     * 字典值
     */
    @Schema(title = "字典值", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;
    /**
     * 字典标签
     */
    @Schema(title = "字典标签", requiredMode = Schema.RequiredMode.REQUIRED)
    private String label;
    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remark;
    /**
     * 字典排序
     */
    @Schema(title = "字典排序")
    private Integer dictSort;
}
