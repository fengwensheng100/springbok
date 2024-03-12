package cn.code4java.springbok.mapper.api;

import cn.code4java.springbok.dto.SaleOrderQueryDTO;
import cn.code4java.springbok.vo.api.SaleOrderApiVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.entity.SaleOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName SaleOrderApiMapper
 * @Description: SaleOrderApiMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface SaleOrderApiMapper extends BaseMapper<SaleOrder> {
    Page<SaleOrderApiVO> pageSaleOrder(Page page, @Param(value = "query") SaleOrderQueryDTO saleOrderQueryDTO);
}
