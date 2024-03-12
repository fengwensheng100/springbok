package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.code4java.springbok.entity.PurchaseOutOrderLine;
import cn.code4java.springbok.vo.PurchaseOutOrderLineVO;

import java.util.List;

/**
 * @ClassName PurchaseOutOrderLineMapper
 * @Description: PurchaseOutOrderLineMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface PurchaseOutOrderLineMapper extends BaseMapper<PurchaseOutOrderLine> {
    List<PurchaseOutOrderLineVO> listByPurchaseOutOrderId(Integer purchaseOutOrderId);
}
