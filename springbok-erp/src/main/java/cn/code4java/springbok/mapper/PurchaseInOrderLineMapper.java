package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.code4java.springbok.entity.PurchaseInOrderLine;
import cn.code4java.springbok.vo.PurchaseInOrderLineVO;

import java.util.List;

/**
 * @ClassName PurchaseInOrderLineMapper
 * @Description: PurchaseInOrderLineMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface PurchaseInOrderLineMapper extends BaseMapper<PurchaseInOrderLine> {
    List<PurchaseInOrderLineVO> listByPurchaseInOrderId(Integer purchaseInOrderId);
}
