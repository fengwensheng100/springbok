package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.PurchaseInOrderQueryDTO;
import cn.code4java.springbok.entity.PurchaseInOrder;
import cn.code4java.springbok.vo.PurchaseInOrderVO;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName PurchaseInOrderMapper
 * @Description: PurchaseInOrderMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface PurchaseInOrderMapper extends BaseMapper<PurchaseInOrder> {
    Page<PurchaseInOrderVO> pagePurchaseInOrder(Page page, @Param(value = "query") PurchaseInOrderQueryDTO purchaseInOrderQueryDTO);
}
