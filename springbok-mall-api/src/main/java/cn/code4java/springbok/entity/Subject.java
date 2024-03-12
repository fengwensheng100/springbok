package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Subject
 * @Description: 专题
 * @Author fengwensheng
 * @Date 2024/1/29
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_subject")
public class Subject extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer subjectId;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String subjectDescription;
    /**
     * 专题图片
     */
    private String image;
    /**
     * 状态
     * 1：启用
     * 2：停用
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
}
