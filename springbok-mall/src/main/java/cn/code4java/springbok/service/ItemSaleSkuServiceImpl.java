package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.ItemSaleSkuQueryDTO;
import cn.code4java.springbok.mapper.ItemSaleSkuMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.ItemSaleSku;
import cn.code4java.springbok.vo.ItemSaleSkuVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @ClassName ItemSaleSkuServiceImpl
 * @Description: 商品SKU服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class ItemSaleSkuServiceImpl extends ServiceImpl<ItemSaleSkuMapper, ItemSaleSku> implements ItemSaleSkuService {

    private ItemSaleSkuMapper itemSaleSkuMapper;

    /**
     * 分页查询商品sku
     *
     * @param itemSaleSkuQueryDTO
     * @return
     */
    @Override
    public Page<ItemSaleSkuVO> pageItemSaleSku(ItemSaleSkuQueryDTO itemSaleSkuQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(itemSaleSkuQueryDTO.getCurrent());
        page.setSize(itemSaleSkuQueryDTO.getSize());
        return itemSaleSkuMapper.pageItemSaleSku(page, itemSaleSkuQueryDTO);
    }
}
