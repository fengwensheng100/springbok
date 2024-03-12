package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.*;
import cn.code4java.springbok.entity.Stock;
import cn.code4java.springbok.entity.StockLine;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.StockLineMapper;
import cn.code4java.springbok.mapper.StockMapper;
import cn.code4java.springbok.vo.StockLineVO;
import cn.code4java.springbok.vo.StockVO;
import cn.code4java.springbok.dto.*;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName StockServiceImpl
 * @Description: 库存服务实现类
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    private StockMapper stockMapper;
    private StockLineMapper stockLineMapper;

    /**
     * 销售商品批量出入库
     *
     * @param stockInOutByItemSaleDTOS
     * @return
     */
    @Override
    @Transactional
    public boolean inOutStockByItemSaleBatch(List<StockInOutByItemSaleDTO> stockInOutByItemSaleDTOS) {
        if (CollectionUtil.isEmpty(stockInOutByItemSaleDTOS)) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, "出入库商品不能为空");
        }
        stockInOutByItemSaleDTOS.stream().forEach(stockInOutByItemSaleDTO -> {
            this.inOutStockByItemSale(stockInOutByItemSaleDTO);
        });
        return true;
    }

    /**
     * 销售商品出入库
     *
     * @param stockInOutByItemSaleDTO
     * @return
     */
    @Override
    @Transactional
    public boolean inOutStockByItemSale(StockInOutByItemSaleDTO stockInOutByItemSaleDTO) {
        if (CollectionUtil.isEmpty(stockInOutByItemSaleDTO.getItemSaleSkuList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, "出入库商品不能为空");
        }
        List<StockInOutDTO.StockInOutLine> stockInOutLines = new LinkedList<>();
        stockInOutByItemSaleDTO.getItemSaleSkuList().stream().forEach(itemSaleSku -> {
            StockInOutDTO.StockInOutLine stockInOutLine = new StockInOutDTO.StockInOutLine();
            stockInOutLine.setSkuCode(itemSaleSku.getSkuCode());
            stockInOutLine.setQuantity(itemSaleSku.getQuantity());
            stockInOutLines.add(stockInOutLine);
        });
        StockInOutDTO stockInOutDTO = new StockInOutDTO();
        BeanUtils.copyProperties(stockInOutByItemSaleDTO, stockInOutDTO);
        stockInOutDTO.setStockInOutLines(stockInOutLines);
        return inOutStock(stockInOutDTO);
    }

    /**
     * 基础商品库存出入库
     *
     * @param stockInOutDTO
     * @return
     */
    @Override
    @Transactional
    public boolean inOutStock(StockInOutDTO stockInOutDTO) {
        stockInOutDTO.getStockInOutLines().stream().forEach(stockInOutLine -> {
            Stock stock = stockMapper.selectOne(new LambdaQueryWrapper<Stock>()
                    .eq(Stock::getSkuCode, stockInOutLine.getSkuCode()));
            if (stock == null) {
                //增加库存记录
                stock = new Stock();
                stock.setSkuCode(stockInOutLine.getSkuCode());
                stock.setQuantity(stockInOutLine.getQuantity());
                stockMapper.insert(stock);
            } else {
                //修改库存记录
                stock.setQuantity(stock.getQuantity().add(stockInOutLine.getQuantity()));
                stockMapper.updateById(stock);
            }
            //库存流水
            StockLine stockLine = new StockLine();
            stockLine.setStockId(stock.getStockId());
            stockLine.setOrderNo(stockInOutDTO.getOrderNo());
            stockLine.setSkuCode(stockInOutLine.getSkuCode());
            stockLine.setQuantity(stockInOutLine.getQuantity());
            stockLine.setOrderType(stockInOutDTO.getBillType());
            stockLineMapper.insert(stockLine);
        });
        return true;
    }

    /**
     * 分页查询库存
     *
     * @param stockQueryDTO
     * @return
     */
    @Override
    public Page<StockVO> pageStock(StockQueryDTO stockQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(stockQueryDTO.getCurrent());
        page.setSize(stockQueryDTO.getSize());
        return stockMapper.pageStock(page, stockQueryDTO);
    }

    /**
     * 查询库存列表
     *
     * @param stockDTO
     * @return
     */
    @Override
    public List<StockVO> listStock(StockDTO stockDTO) {
        return stockMapper.listStock(stockDTO);
    }

    /**
     * 根据id查询库存
     *
     * @param stockId
     * @return
     */
    @Override
    public StockVO selectStockById(int stockId) {
        StockVO stockVO = new StockVO();
        Stock stock = stockMapper.selectById(stockId);
        BeanUtils.copyProperties(stock, stockVO);
        List<StockLine> stockLines = stockLineMapper.selectList(new LambdaQueryWrapper<StockLine>().eq(StockLine::getStockId, stockId));
        stockVO.setStockLine(stockLines);
        return stockVO;
    }

    /**
     * 分页查询库存明细
     *
     * @param stockLineQueryDTO
     * @return
     */
    @Override
    public Page<StockLineVO> pageStockLine(StockLineQueryDTO stockLineQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(stockLineQueryDTO.getCurrent());
        page.setSize(stockLineQueryDTO.getSize());
        return stockLineMapper.pageStockLine(page, stockLineQueryDTO);
    }
}
