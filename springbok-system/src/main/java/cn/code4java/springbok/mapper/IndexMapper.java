package cn.code4java.springbok.mapper;

import cn.code4java.springbok.vo.ItemSaleStatisticsVO;
import cn.code4java.springbok.vo.StatisticsVO;

import java.util.List;

/**
 * @ClassName IndexMapper
 * @Description: IndexMapper
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
public interface IndexMapper {
    StatisticsVO statistics();
    List<ItemSaleStatisticsVO> selectTopItemSaleStatisticsByMonth();
}
