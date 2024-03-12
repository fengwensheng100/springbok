package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Spec
 * @Description: 规格
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_spec")
public class Spec extends BaseEntity {

    /**
     * 规格id
     */
    @TableId(type = IdType.AUTO)
    private Integer specId;
    /**
     * 规格名称
     */
    private String specName;
    /**
     * sku字符串
     */
    private String skuStr;
    /**
     * 备注
     */
    private String remark;
}
