package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description: 基础通用实体类
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
public class BaseEntity {

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdName;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updatedName;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updatedTime;
}
