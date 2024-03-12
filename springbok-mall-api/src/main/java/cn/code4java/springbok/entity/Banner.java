package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Banner
 * @Description: 广告
 * @Author fengwensheng
 * @Date 2024/1/29
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_banner")
public class Banner extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer bannerId;
    /**
     * 标题
     */
    private String title;
    /**
     * 广告图片
     */
    private String image;
    /**
     * 跳转页面
     */
    private String toPage;
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
