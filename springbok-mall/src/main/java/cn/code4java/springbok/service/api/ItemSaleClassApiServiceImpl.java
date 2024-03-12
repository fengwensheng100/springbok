package cn.code4java.springbok.service.api;

import cn.code4java.springbok.dto.ItemSaleClassQueryDTO;
import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.entity.ItemSaleClass;
import cn.code4java.springbok.mapper.ItemSaleClassMapper;
import cn.code4java.springbok.mapper.ItemSaleMapper;
import cn.code4java.springbok.vo.api.ItemSaleClassApiVO;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ItemSaleClassApiServiceImpl
 * @Description: 商品分类API服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class ItemSaleClassApiServiceImpl extends ServiceImpl<ItemSaleMapper, ItemSale> implements ItemSaleClassApiService {

    private ItemSaleMapper itemSaleMapper;
    private ItemSaleClassMapper itemSaleClassMapper;

    /**
     * 查询商品分类列表
     * @param itemSaleClassQueryDTO
     * @return
     */
    @Override
    public List<ItemSaleClass> listItemSaleClass(ItemSaleClassQueryDTO itemSaleClassQueryDTO) {
        List<ItemSaleClass> itemSaleClasses = itemSaleClassMapper.selectList(new LambdaQueryWrapper(itemSaleClassQueryDTO));
        return itemSaleClasses;
    }

    /**
     * 根据id查询商品分类
     * @param itemSaleClassId
     * @return
     */
    @Override
    public ItemSaleClassApiVO selectItemSaleClassById(int itemSaleClassId) {
        ItemSaleClass itemSaleClass = itemSaleClassMapper.selectById(itemSaleClassId);
        ItemSaleClassApiVO itemSaleClassApiVO = new ItemSaleClassApiVO();
        BeanUtil.copyProperties(itemSaleClass, itemSaleClassApiVO);

        List<ItemSaleClass> itemSaleClasses = itemSaleClassMapper.selectList(Wrappers.emptyWrapper());
        List<ItemSaleClassApiVO> itemSaleClassApiVOS =  BeanUtil.copyToList(itemSaleClasses, ItemSaleClassApiVO.class);

        this.buildItemSaleClassTree(itemSaleClassApiVO, itemSaleClassApiVOS);
        return itemSaleClassApiVO;
    }

    public void buildItemSaleClassTree(ItemSaleClassApiVO itemSaleClassApiVO, List<ItemSaleClassApiVO> itemSaleClassApiVOS) {
        List<ItemSaleClassApiVO> childrenItemSaleClass = itemSaleClassApiVOS.stream().filter(i -> i.getParentId().intValue() == itemSaleClassApiVO.getItemSaleClassId().intValue()).collect(Collectors.toList());
        if (!childrenItemSaleClass.isEmpty()) {
            for (ItemSaleClassApiVO children : childrenItemSaleClass) {
                buildItemSaleClassTree(children, itemSaleClassApiVOS);
            }
            itemSaleClassApiVO.setChildrenList(childrenItemSaleClass);
        }
        // 加入展示的商品
        List<ItemSale> itemSales = itemSaleMapper.selectList(new LambdaQueryWrapper<ItemSale>().eq(ItemSale::getItemSaleClassCode, itemSaleClassApiVO.getItemSaleClassCode()).last(" limit 9"));
        itemSaleClassApiVO.setItemSales(itemSales);
    }
}
