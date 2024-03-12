package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.StockDTO;
import cn.code4java.springbok.dto.StockQueryDTO;
import cn.code4java.springbok.entity.Stock;
import cn.code4java.springbok.vo.StockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName StockMapper
 * @Description: StockMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface StockMapper extends BaseMapper<Stock> {
    Page<StockVO> pageStock(Page page, @Param(value = "query") StockQueryDTO stockQueryDTO);
    List<StockVO> listStock(StockDTO stockDTO);
}
