package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.ItemSaleQueryDTO;
import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.vo.ItemSaleVO;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName ItemSaleMapper
 * @Description: ItemSaleMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface ItemSaleMapper extends BaseMapper<ItemSale> {
    ItemSaleVO selectItemSaleById(int itemSaleId);
    Page<ItemSale> pageItemSale(Page<ItemSale> page, @Param(value = "query") ItemSaleQueryDTO itemSaleQueryDTO);
}
