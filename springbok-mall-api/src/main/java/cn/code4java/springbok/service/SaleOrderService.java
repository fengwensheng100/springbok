package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.SaleOrderQueryDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.entity.SaleOrder;
import cn.code4java.springbok.vo.SaleOrderVO;

/**
 * @ClassName SaleOrderService
 * @Description: 销售订单服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface SaleOrderService extends IService<SaleOrder> {
    /**
     * 分页查询销售订单
     *
     * @param saleOrderQueryDTO
     * @return
     */
    Page<SaleOrderVO> pageSaleOrder(SaleOrderQueryDTO saleOrderQueryDTO);

    /**
     * 根据id查询销售订单
     *
     * @param id
     * @return
     */
    SaleOrderVO selectSaleOrderById(Integer id);

    /**
     * 订单发货
     *
     * @param id
     * @return
     */
    boolean deliverSaleOrder(Integer id);
}
