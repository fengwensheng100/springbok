package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.ItemSaleDTO;
import cn.code4java.springbok.dto.ItemSaleQueryDTO;
import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.entity.ItemSaleSku;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.ItemSaleMapper;
import cn.code4java.springbok.mapper.ItemSaleSkuMapper;
import cn.code4java.springbok.vo.ItemSaleSkuVO;
import cn.code4java.springbok.vo.ItemSaleVO;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName ItemSaleServiceImpl
 * @Description: 商品服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class ItemSaleServiceImpl extends ServiceImpl<ItemSaleMapper, ItemSale> implements ItemSaleService {

    private ItemSaleMapper itemSaleMapper;
    private ItemSaleSkuMapper itemSaleSkuMapper;

    /**
     * 分页查询商品
     *
     * @param itemSaleQueryDTO
     * @return
     */
    @Override
    public Page<ItemSale> pageItemSale(ItemSaleQueryDTO itemSaleQueryDTO) {
        Page<ItemSale> page = new Page<>();
        page.setCurrent(itemSaleQueryDTO.getCurrent());
        page.setSize(itemSaleQueryDTO.getSize());
        return itemSaleMapper.pageItemSale(page, itemSaleQueryDTO);
    }

    /**
     * 查询商品列表
     *
     * @param itemSale
     * @return
     */
    @Override
    public List<ItemSale> listItemSale(ItemSale itemSale) {
        LambdaQueryWrapper<ItemSale> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(itemSale.getItemSaleClassCode()), ItemSale::getItemSaleClassCode, itemSale.getItemSaleClassCode());
        wrapper.like(StringUtils.isNotBlank(itemSale.getItemSaleName()), ItemSale::getItemSaleName, itemSale.getItemSaleName());
        List<ItemSale> itemSales = itemSaleMapper.selectList(wrapper);
        return itemSales;
    }

    /**
     * 根据id查询商品
     *
     * @param itemSaleId
     * @return
     */
    @Override
    public ItemSaleVO selectItemSaleById(int itemSaleId) {
        ItemSaleVO itemSaleVO = itemSaleMapper.selectItemSaleById(itemSaleId);
        List<ItemSaleSkuVO> itemSaleSkus = itemSaleSkuMapper.selectItemSaleSkuByItemSaleId(itemSaleId);
        if (CollectionUtil.isNotEmpty(itemSaleSkus)) {
            itemSaleVO.setItemSaleSkuList(itemSaleSkus);
        }
        itemSaleVO.setSpecList(JSONUtil.toList(itemSaleVO.getSpecJson(), ItemSale.Spec.class));
        itemSaleVO.setPropertyList(JSONUtil.toList(itemSaleVO.getPropertyJson(), ItemSale.Property.class));
        itemSaleVO.setMainImageList(ListUtil.toList(itemSaleVO.getMainImage().split(",")));
        return itemSaleVO;
    }

    /**
     * 新增商品
     *
     * @param itemSaleDTO
     * @return
     */
    @Override
    @Transactional
    public int addItemSale(ItemSaleDTO itemSaleDTO) {
        if (CollectionUtil.isEmpty(itemSaleDTO.getItemSaleSkuList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR.getMessage());
        }
        if (itemSaleDTO.getItemSaleSkuList().stream().map(ItemSaleSku::getSkuCode).distinct().collect(Collectors.toList()).size() != itemSaleDTO.getItemSaleSkuList().size()) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_ERROR, "规格编码不能重复");
        }
        Optional<ItemSaleSku> first = itemSaleDTO.getItemSaleSkuList().stream().sorted(Comparator.comparing(ItemSaleSku::getPrice)).findFirst();
        itemSaleDTO.setItemSalePrice(first.get().getPrice());
        itemSaleDTO.setSpecJson(JSONUtil.toJsonStr(itemSaleDTO.getSpecList()));
        itemSaleMapper.insert(itemSaleDTO);
        itemSaleDTO.getItemSaleSkuList().stream().forEach(itemSaleSku -> {
            itemSaleSku.setItemSaleId(itemSaleDTO.getItemSaleId());
            itemSaleSku.setItemCode(itemSaleDTO.getItemCode());
            itemSaleSku.setItemName(itemSaleDTO.getItemName());
            itemSaleSkuMapper.insert(itemSaleSku);
        });
        return 1;
    }

    /**
     * 修改商品
     *
     * @param itemSaleDTO
     * @return
     */
    @Override
    @Transactional
    public int updateItemSale(ItemSaleDTO itemSaleDTO) {
        if (CollectionUtil.isEmpty(itemSaleDTO.getItemSaleSkuList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR.getMessage());
        }
        if (itemSaleDTO.getItemSaleSkuList().stream().map(ItemSaleSku::getSkuCode).distinct().collect(Collectors.toList()).size() != itemSaleDTO.getItemSaleSkuList().size()) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_ERROR, "规格编码不能重复");
        }
        itemSaleSkuMapper.delete(new LambdaQueryWrapper<ItemSaleSku>().eq(ItemSaleSku::getItemSaleId, itemSaleDTO.getItemSaleId()));
        Optional<ItemSaleSku> first = itemSaleDTO.getItemSaleSkuList().stream().sorted(Comparator.comparing(ItemSaleSku::getPrice)).findFirst();
        itemSaleDTO.setItemSalePrice(first.get().getPrice());
        itemSaleDTO.setSpecJson(JSONUtil.toJsonStr(itemSaleDTO.getSpecList()));
        itemSaleMapper.updateById(itemSaleDTO);
        itemSaleDTO.getItemSaleSkuList().stream().forEach(itemSaleSku -> {
            itemSaleSku.setItemSaleId(itemSaleDTO.getItemSaleId());
            itemSaleSku.setItemCode(itemSaleDTO.getItemCode());
            itemSaleSku.setItemName(itemSaleDTO.getItemName());
            itemSaleSkuMapper.insert(itemSaleSku);
        });
        return 1;
    }

    /**
     * 删除商品
     *
     * @param itemSaleId
     * @return
     */
    @Override
    public int deleteItemSale(int itemSaleId) {
        // 已有库存记录不能删除
        itemSaleMapper.deleteById(itemSaleId);
        return itemSaleSkuMapper.delete(new LambdaQueryWrapper<ItemSaleSku>().eq(ItemSaleSku::getItemSaleId, itemSaleId));
    }
}
