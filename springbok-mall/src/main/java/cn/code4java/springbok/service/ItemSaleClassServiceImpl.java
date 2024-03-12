package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.ItemSaleClassQueryDTO;
import cn.code4java.springbok.entity.ItemSaleClass;
import cn.code4java.springbok.mapper.ItemSaleClassMapper;
import cn.code4java.springbok.utils.ListUtils;
import cn.code4java.springbok.vo.ItemSaleClassVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ItemSaleClassServiceImpl
 * @Description: 商品分类服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class ItemSaleClassServiceImpl extends ServiceImpl<ItemSaleClassMapper, ItemSaleClass> implements ItemSaleClassService {

    private ItemSaleClassMapper itemSaleClassMapper;

    /**
     * 分页查询商品分类
     *
     * @param itemSaleClassQueryDTO
     * @return
     */
    @Override
    public Page<ItemSaleClassVO> pageItemSaleClass(ItemSaleClassQueryDTO itemSaleClassQueryDTO) {
        Page<ItemSaleClassVO> page = new Page<>();
        page.setCurrent(itemSaleClassQueryDTO.getCurrent());
        page.setSize(itemSaleClassQueryDTO.getSize());
        List<ItemSaleClassVO> itemSaleClassesVOS = BeanUtil.copyToList(itemSaleClassMapper.selectList(Wrappers.emptyWrapper()), ItemSaleClassVO.class);
        if (CollectionUtil.isNotEmpty(itemSaleClassesVOS)) {
            List<ItemSaleClassVO> rootItemSaleClassVOS = new LinkedList<>();
            for (ItemSaleClassVO saleClass : itemSaleClassesVOS) {
                if (saleClass.getParentId() == 0) {
                    rootItemSaleClassVOS.add(saleClass);
                }
            }
            for (ItemSaleClassVO saleClass : rootItemSaleClassVOS) {
                buildItemSaleClassTree(saleClass, itemSaleClassesVOS);
            }
            List<ItemSaleClassVO> records = ListUtils.page(rootItemSaleClassVOS, itemSaleClassQueryDTO.getCurrent(), itemSaleClassQueryDTO.getSize());
            page.setRecords(records);
            page.setTotal(rootItemSaleClassVOS.size());
            page.setPages(rootItemSaleClassVOS.size() > rootItemSaleClassVOS.size() ? rootItemSaleClassVOS.size() / itemSaleClassQueryDTO.getSize() : 1);
        }
        return page;
    }

    /**
     * 查询商品分类列表
     *
     * @param itemSaleClass
     * @return
     */
    @Override
    public List<ItemSaleClass> listItemSaleClass(ItemSaleClass itemSaleClass) {
        List<ItemSaleClass> itemSaleClasses = itemSaleClassMapper.selectList(new LambdaQueryWrapper<>());
        return itemSaleClasses;
    }

    /**
     * 构建商品分类树
     *
     * @param itemSaleClassVO
     * @param itemSaleClassVOS
     */
    @Override
    public void buildItemSaleClassTree(ItemSaleClassVO itemSaleClassVO, List<ItemSaleClassVO> itemSaleClassVOS) {
        List<ItemSaleClassVO> childrenItemSaleClass = itemSaleClassVOS.stream().filter(i -> i.getParentId().intValue() == itemSaleClassVO.getItemSaleClassId().intValue()).collect(Collectors.toList());
        if (!childrenItemSaleClass.isEmpty()) {
            for (ItemSaleClassVO children : childrenItemSaleClass) {
                buildItemSaleClassTree(children, itemSaleClassVOS);
            }
            itemSaleClassVO.setChildrenList(childrenItemSaleClass);
        }
    }

    /**
     * 新增商品分类
     *
     * @param itemSaleClass
     * @return
     */
    @Override
    public int addItemSaleClass(ItemSaleClass itemSaleClass) {
        return itemSaleClassMapper.insert(itemSaleClass);
    }

    /**
     * 修改商品分类
     *
     * @param itemSaleClass
     * @return
     */
    @Override
    public int updateItemSaleClass(ItemSaleClass itemSaleClass) {
        return itemSaleClassMapper.updateById(itemSaleClass);
    }

    /**
     * 删除商品分类
     *
     * @param itemSaleClassId
     * @return
     */
    @Override
    public int deleteItemSaleClass(int itemSaleClassId) {
        return itemSaleClassMapper.deleteById(itemSaleClassId);
    }
}
