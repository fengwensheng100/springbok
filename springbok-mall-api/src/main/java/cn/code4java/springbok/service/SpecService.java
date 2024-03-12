package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.SpecDTO;
import cn.code4java.springbok.dto.SpecQueryDTO;
import cn.code4java.springbok.entity.Spec;
import cn.code4java.springbok.vo.SpecVO;

import java.util.List;

/**
 * @ClassName SpecService
 * @Description: 规格服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface SpecService {
    /**
     * 分页查询规格
     *
     * @param propertyQueryDTO
     * @return
     */
    Page<Spec> pageSpec(SpecQueryDTO propertyQueryDTO);

    /**
     * 查询规格列表
     *
     * @param spec
     * @return
     */
    List<Spec> listSpec(Spec spec);

    /**
     * 根据id查询规格
     *
     * @param propertyId
     * @return
     */
    SpecVO selectSpecById(int propertyId);

    /**
     * 根据多个id查询规格
     *
     * @param propertyIds
     * @return
     */
    List<SpecVO> selectSpecByIds(String propertyIds);

    /**
     * 新增规格
     *
     * @param propertyDTO
     * @return
     */
    int addSpec(SpecDTO propertyDTO);

    /**
     * 修改规格
     *
     * @param propertyDTO
     * @return
     */
    int updateSpec(SpecDTO propertyDTO);

    /**
     * 删除规格
     *
     * @param propertyId
     * @return
     */
    int deleteSpec(int propertyId);
}
