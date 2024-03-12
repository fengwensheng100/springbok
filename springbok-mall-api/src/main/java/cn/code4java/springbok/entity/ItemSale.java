package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName ItemSale
 * @Description: 销售商品（标准产品单元，即SPU）
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_item_sale")
public class ItemSale extends BaseEntity {

    /**
     * 销售商品id
     */
    @TableId(type = IdType.AUTO)
    private Integer itemSaleId;
    /**
     * 商品编码
     */
    private String itemCode;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 销售商品名称
     */
    private String itemSaleName;
    /**
     * 销售价格
     */
    private BigDecimal itemSalePrice;
    /**
     * 销售商品描述
     */
    private String itemSaleDescription;
    /**
     * 营销分类编码
     */
    private String itemSaleClassCode;
    /**
     * 标签数组
     */
    private String tagIds;

    /**
     * 商品主图
     */
    private String mainImage;

    /**
     * 状态
     * 1：上架
     * 2：下架
     */
    private Integer status;

    /**
     * 规格列表
     */
    @TableField(exist = false)
    private List<String> mainImageList;

    /**
     * 规格json
     */
    private String specJson;

    /**
     * 规格列表
     */
    @TableField(exist = false)
    private List<Spec> specList;

    @Data
    public static class Spec {
        /**
         * 规格名
         */
        private String specName;
        /**
         * 规格项
         */
        private List<String> specValueNameList;
    }

    /**
     * 属性json
     */
    private String propertyJson;

    /**
     * 属性列表
     */
    @TableField(exist = false)
    private List<Property> propertyList;

    @Data
    public static class Property {
        /**
         * 属性名
         */
        private String propertyName;
        /**
         * 属性值
         */
        private String propertyValue;
    }
}
