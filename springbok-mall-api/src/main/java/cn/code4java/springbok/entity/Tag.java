package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Tag
 * @Description: 标签
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_tag")
public class Tag extends BaseEntity {

    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    private Integer tagId;
    /**
     * 标签类型
     * 1：商品
     * 2：用户
     */
    private Integer tagType;
    /**
     * 标签名称
     */
    private String tagName;
}
