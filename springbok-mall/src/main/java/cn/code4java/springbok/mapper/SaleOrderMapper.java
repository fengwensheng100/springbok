package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.SaleOrderQueryDTO;
import cn.code4java.springbok.entity.SaleOrder;
import cn.code4java.springbok.vo.SaleOrderVO;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName SaleOrderMapper
 * @Description: SaleOrderMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface SaleOrderMapper extends BaseMapper<SaleOrder> {
    Page<SaleOrderVO> pageSaleOrder(Page page, @Param(value = "query") SaleOrderQueryDTO saleOrderQueryDTO);
}
