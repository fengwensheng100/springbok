package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.StockLineQueryDTO;
import cn.code4java.springbok.entity.StockLine;
import cn.code4java.springbok.vo.StockLineVO;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName StockLineMapper
 * @Description: StockLineMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface StockLineMapper extends BaseMapper<StockLine> {
    Page<StockLineVO> pageStockLine(Page page, @Param(value = "query") StockLineQueryDTO stockLineQueryDTO);
}
