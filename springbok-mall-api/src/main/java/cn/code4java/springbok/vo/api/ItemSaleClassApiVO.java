package cn.code4java.springbok.vo.api;

import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.entity.ItemSaleClass;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ItemSaleClassApiVO
 * @Description: ItemSaleClassApiVO
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
@Data
public class ItemSaleClassApiVO extends ItemSaleClass {

    /**
     * 子分类
     */
    private List<ItemSaleClassApiVO> childrenList;

    /**
     * 展示商品
     */
    private List<ItemSale> itemSales;
}
