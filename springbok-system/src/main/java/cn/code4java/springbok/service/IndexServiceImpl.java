package cn.code4java.springbok.service;

import cn.code4java.springbok.mapper.IndexMapper;
import cn.code4java.springbok.vo.ItemSaleStatisticsVO;
import cn.code4java.springbok.vo.StatisticsVO;
import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.utils.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName IndexServiceImpl
 * @Description: 首页服务实现类
 * @Author fengwensheng
 * @Date 2024/2/4
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

    private IndexMapper indexMapper;
    private RedisUtils redisUtils;

    /**
     * 首页数据统计
     *
     * @return
     */
    @Override
    public StatisticsVO statistics() {
        StatisticsVO statistics = indexMapper.statistics();
        // 访问人数统计
        Map<String, String> entries = redisUtils.entries(SpringbokConstant.KEY_LOGIN_STATISTICS);
        List<StatisticsVO.LoginStatistics> loginStatistics = new LinkedList<>();
        TreeSet<String> treeSet = new TreeSet<>(entries.keySet());
        for (String key : treeSet) {
            StatisticsVO.LoginStatistics loginStatistic = new StatisticsVO.LoginStatistics();
            loginStatistic.setDate(key);
            loginStatistic.setCount(Long.valueOf(entries.get(key)));
            loginStatistics.add(loginStatistic);
        }
        statistics.setLoginStatistics(loginStatistics);
        // 销售额前10商品
        List<ItemSaleStatisticsVO> itemSaleStatisticsVOS = indexMapper.selectTopItemSaleStatisticsByMonth();
        statistics.setTopItemSaleStatisticsByMonth(itemSaleStatisticsVOS);
        return statistics;
    }
}
