package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.ItemSaleSkuQueryDTO;
import cn.code4java.springbok.entity.ItemSaleSku;
import cn.code4java.springbok.vo.ItemSaleSkuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ItemSaleSkuMapper
 * @Description: ItemSaleSkuMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface ItemSaleSkuMapper extends BaseMapper<ItemSaleSku> {
    Page<ItemSaleSkuVO> pageItemSaleSku(Page page, @Param(value = "query") ItemSaleSkuQueryDTO itemSaleSkuQueryDTO);
    List<ItemSaleSkuVO> selectItemSaleSkuByItemSaleId(int itemSaleId);
}
