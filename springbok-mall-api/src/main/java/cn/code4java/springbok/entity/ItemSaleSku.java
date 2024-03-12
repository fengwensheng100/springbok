package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName ItemSaleSku
 * @Description: 销售商品sku
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_item_sale_sku")
public class ItemSaleSku {

    /**
     * 销售商品规格id
     */
    @TableId(type = IdType.AUTO)
    private Integer itemSaleSkuId;
    /**
     * 商品编码
     */
    private String itemCode;
    /**
     * 销售商品id
     */
    private Integer itemSaleId;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * sku编码
     */
    private String skuCode;
    /**
     * 售价
     */
    private BigDecimal price;
    /**
     * 原价
     */
    private BigDecimal sourcePrice;
    /**
     * sku图片
     */
    private String image;
    /**
     * 规格名称
     */
    private String spec;
    /**
     * 商品规格json
     */
    private String specMapJson;
    /**
     * 商品规格map
     */
    @TableField(exist = false)
    private Map<String, String> specMap;
}
