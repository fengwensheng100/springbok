package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName ItemSaleClass
 * @Description: 商品分类
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_item_sale_class")
public class ItemSaleClass extends BaseEntity {

    /**
     * 营销分类id
     */
    @TableId(type = IdType.AUTO)
    private Integer itemSaleClassId;
    /**
     * 营销分类编码
     */
    private String itemSaleClassCode;
    /**
     * 营销分类名称
     */
    private String itemSaleClassName;
    /**
     * 上级id
     */
    private Integer parentId;
    /**
     * 分类图标
     */
    private String classIcon;
    /**
     * 是否首页展示
     */
    private Boolean showIndex;
}
