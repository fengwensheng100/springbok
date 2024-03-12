package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.PurchaseOutOrderQueryDTO;
import cn.code4java.springbok.entity.PurchaseOutOrder;
import cn.code4java.springbok.vo.PurchaseOutOrderVO;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName PurchaseOutOrderMapper
 * @Description: PurchaseOutOrderMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface PurchaseOutOrderMapper extends BaseMapper<PurchaseOutOrder> {
    Page<PurchaseOutOrderVO> pagePurchaseOutOrder(Page page, @Param(value = "query") PurchaseOutOrderQueryDTO purchaseOutOrderQueryDTO);
}
