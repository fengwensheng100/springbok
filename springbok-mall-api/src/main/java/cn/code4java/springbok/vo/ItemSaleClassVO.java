package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.ItemSaleClass;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ItemSaleClassVO
 * @Description: ItemSaleClassVO
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
@Data
public class ItemSaleClassVO extends ItemSaleClass {

    /**
     * 子分类
     */
    private List<ItemSaleClassVO> childrenList;
}
