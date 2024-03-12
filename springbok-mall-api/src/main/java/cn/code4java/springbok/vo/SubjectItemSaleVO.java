package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.SubjectItemSale;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName SubjectItemSaleVO
 * @Description: SubjectItemSaleVO
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
public class SubjectItemSaleVO extends SubjectItemSale {
    /**
     * 商品图片
     */
    private String mainImage;
    /**
     * 商品id
     */
    private String itemSaleId;
    /**
     * 商品编码
     */
    private String itemCode;
    /**
     * 销售商品名称
     */
    private String itemSaleName;
    /**
     * 销售价格
     */
    private BigDecimal itemSalePrice;
}
