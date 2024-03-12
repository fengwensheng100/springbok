package cn.code4java.springbok.service;

import cn.code4java.springbok.entity.SpecValue;

import java.util.List;

/**
 * @ClassName SpecValueService
 * @Description: 规格值服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface SpecValueService {
    /**
     * 根据规格id列表查询规格值
     *
     * @param specIds
     * @return
     */
    List<SpecValue> listSpecValueBySpecIds(List<Integer> specIds);
}
