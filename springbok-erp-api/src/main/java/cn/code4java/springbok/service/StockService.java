package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.*;
import cn.code4java.springbok.vo.StockLineVO;
import cn.code4java.springbok.vo.StockVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @ClassName StockService
 * @Description: 庫存服务类
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface StockService {

    /**
     * 销售商品批量出入库
     *
     * @param stockInOutByItemSaleDTOS
     * @return
     */
    boolean inOutStockByItemSaleBatch(List<StockInOutByItemSaleDTO> stockInOutByItemSaleDTOS);

    /**
     * 销售商品出入库
     *
     * @param stockInOutByItemSaleDTO
     * @return
     */
    boolean inOutStockByItemSale(StockInOutByItemSaleDTO stockInOutByItemSaleDTO);

    /**
     * 基础商品库存出入库
     *
     * @param stockInOutDTO
     * @return
     */
    boolean inOutStock(StockInOutDTO stockInOutDTO);

    /**
     * 分页查询库存
     *
     * @param stockQueryDTO
     * @return
     */
    Page<StockVO> pageStock(StockQueryDTO stockQueryDTO);

    /**
     * 查询库存列表
     *
     * @param stockDTO
     * @return
     */
    List<StockVO> listStock(StockDTO stockDTO);

    /**
     * 根据id查询库存
     *
     * @param stockId
     * @return
     */
    StockVO selectStockById(int stockId);

    /**
     * 分页查询库存明细
     *
     * @param stockLineQueryDTO
     * @return
     */
    Page<StockLineVO> pageStockLine(StockLineQueryDTO stockLineQueryDTO);
}
