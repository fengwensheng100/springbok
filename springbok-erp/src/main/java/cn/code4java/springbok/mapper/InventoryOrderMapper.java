package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.InventoryOrderQueryDTO;
import cn.code4java.springbok.entity.InventoryOrder;
import cn.code4java.springbok.vo.InventoryOrderVO;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName InventoryOrderMapper
 * @Description: InventoryOrderMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface InventoryOrderMapper extends BaseMapper<InventoryOrder> {
    Page<InventoryOrderVO> pageInventoryOrder(Page page, @Param(value = "query") InventoryOrderQueryDTO inventoryOrderQueryDTO);
}
