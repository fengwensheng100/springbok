package cn.code4java.springbok.service;

import cn.code4java.springbok.vo.StatisticsVO;

/**
 * @ClassName IndexService
 * @Description: 首页服务类
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
public interface IndexService {
    /**
     * 首页数据统计
     *
     * @return
     */
    StatisticsVO statistics();
}
