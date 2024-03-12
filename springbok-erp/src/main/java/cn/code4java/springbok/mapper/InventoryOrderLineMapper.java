package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.code4java.springbok.entity.InventoryOrderLine;
import cn.code4java.springbok.vo.InventoryOrderLineVO;

import java.util.List;

/**
 * @ClassName InventoryOrderLineMapper
 * @Description: InventoryOrderLineMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface InventoryOrderLineMapper extends BaseMapper<InventoryOrderLine> {
    List<InventoryOrderLineVO> listByInventoryOrderId(Integer inventoryOrderId);
}
