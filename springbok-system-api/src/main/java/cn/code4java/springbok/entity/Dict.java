package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName Dict
 * @Description: 字典
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@Schema(title = "字典实体")
@TableName(value = "sys_dict")
public class Dict extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "id")
    private Integer dictId;
    /**
     * 字典编码
     */
    @Schema(title = "字典编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dictCode;
    /**
     * 字典名称
     */
    @Schema(title = "字典名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dictName;
    /**
     * 字典类型
     * 1：系统字典
     * 2：业务字典
     */
    @Schema(title = "字典类型 1：系统字典 2：业务字典", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer dictType;
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
