package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.SaleOrderQueryDTO;
import cn.code4java.springbok.enums.OrderStatusEnum;
import cn.code4java.springbok.mapper.SaleOrderLineMapper;
import cn.code4java.springbok.mapper.SaleOrderMapper;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.SaleOrder;
import cn.code4java.springbok.entity.SaleOrderLine;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.vo.SaleOrderVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SaleOrderServiceImpl
 * @Description: 销售订单服务实现类
 * @Author fengwensheng
 * @Date 2024/2/2
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements SaleOrderService {

    private SaleOrderMapper saleOrderMapper;
    private SaleOrderLineMapper saleOrderLineMapper;

    /**
     * 分页查询销售订单
     *
     * @param saleOrderQueryDTO
     * @return
     */
    @Override
    public Page<SaleOrderVO> pageSaleOrder(SaleOrderQueryDTO saleOrderQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(saleOrderQueryDTO.getCurrent());
        page.setSize(saleOrderQueryDTO.getSize());
        return saleOrderMapper.pageSaleOrder(page, saleOrderQueryDTO);
    }

    /**
     * 根据id查询销售订单
     *
     * @param id
     * @return
     */
    @Override
    public SaleOrderVO selectSaleOrderById(Integer id) {
        SaleOrder saleOrder = saleOrderMapper.selectById(id);
        SaleOrderVO saleOrderVO = new SaleOrderVO();
        BeanUtil.copyProperties(saleOrder, saleOrderVO);
        List<SaleOrderLine> saleOrderLines = saleOrderLineMapper.selectList(Wrappers.<SaleOrderLine>lambdaQuery().eq(SaleOrderLine::getSaleOrderId, saleOrder.getSaleOrderId()));
        saleOrderVO.setSaleOrderLines(saleOrderLines);
        return saleOrderVO;
    }

    /**
     * 订单发货
     *
     * @param id
     * @return
     */
    @Override
    public boolean deliverSaleOrder(Integer id) {
        SaleOrder saleOrder = saleOrderMapper.selectById(id);
        if (saleOrder.getOrderStatus() != OrderStatusEnum.WAITING_DELIVER.getStatus()) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "订单非待发货状态");
        }
        saleOrder.setOrderStatus(OrderStatusEnum.WAITING_RECEIVED.getStatus());
        saleOrderMapper.updateById(saleOrder);
        return true;
    }
}
