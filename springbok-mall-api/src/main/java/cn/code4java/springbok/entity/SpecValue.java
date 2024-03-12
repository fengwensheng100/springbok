package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Sku
 * @Description: Sku
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_spec_value")
public class SpecValue extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer specValueId;
    /**
     * sku名称
     */
    private String specValueName;
    /**
     * 规格id
     */
    private Integer specId;
}
