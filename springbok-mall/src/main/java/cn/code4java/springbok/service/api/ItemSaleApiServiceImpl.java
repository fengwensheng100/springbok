package cn.code4java.springbok.service.api;

import cn.code4java.springbok.dto.ItemSaleQueryDTO;
import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.mapper.ItemSaleMapper;
import cn.code4java.springbok.mapper.ItemSaleSkuMapper;
import cn.code4java.springbok.vo.ItemSaleSkuVO;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.vo.ItemSaleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ItemSaleApiServiceImpl
 * @Description: 商品API服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class ItemSaleApiServiceImpl extends ServiceImpl<ItemSaleMapper, ItemSale> implements ItemSaleApiService {

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
        Page<ItemSale> itemSalePage = itemSaleMapper.pageItemSale(page, itemSaleQueryDTO);
        itemSalePage.getRecords().stream().forEach(itemSale -> {
            itemSale.setMainImageList(ListUtil.toList(itemSale.getMainImage().split(",")));
        });
        return itemSalePage;
    }

    /**
     * 根据id查询商品
     *
     * @param itemSaleId
     * @return
     * @throws InterruptedException
     */
    @Override
    public ItemSaleVO selectItemSaleById(int itemSaleId) {
        ItemSaleVO itemSaleVO = itemSaleMapper.selectItemSaleById(itemSaleId);
        if (itemSaleVO != null) {
            List<ItemSaleSkuVO> itemSaleSkus = itemSaleSkuMapper.selectItemSaleSkuByItemSaleId(itemSaleId);
            if (CollectionUtil.isNotEmpty(itemSaleSkus)) {
                itemSaleVO.setItemSaleSkuList(itemSaleSkus);
            }
            itemSaleVO.setSpecList(JSONUtil.toList(itemSaleVO.getSpecJson(), ItemSale.Spec.class));
            itemSaleVO.setPropertyList(JSONUtil.toList(itemSaleVO.getPropertyJson(), ItemSale.Property.class));
            itemSaleVO.setMainImageList(ListUtil.toList(itemSaleVO.getMainImage().split(",")));
        }
        return itemSaleVO;
    }
}
