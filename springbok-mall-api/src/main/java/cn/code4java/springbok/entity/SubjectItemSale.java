package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName SubjectItemSale
 * @Description: 专题商品关联
 * @Author fengwensheng
 * @Date 2024/1/29
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_subject_item_sale")
public class SubjectItemSale {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer subjectItemSaleSkuId;
    /**
     * 专题id
     */
    private Integer subjectId;
    /**
     * 商品编码
     */
    private String itemCode;
}
