package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.DictQueryDTO;
import cn.code4java.springbok.entity.Dict;
import cn.code4java.springbok.vo.DictVO;

import java.util.List;

/**
 * @ClassName DictService
 * @Description: 字典服务类
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
public interface DictService {
    /**
     * 分页查询字典
     *
     * @param DictQueryDTO
     * @return
     */
    Page<Dict> pageDict(DictQueryDTO DictQueryDTO);

    /**
     * 查询字典列表
     *
     * @param Dict
     * @return
     */
    List<DictVO> listDict(Dict Dict);

    /**
     * 新增字典
     *
     * @param Dict
     * @return
     */
    int addDict(Dict Dict);

    /**
     * 修改字典
     *
     * @param Dict
     * @return
     */
    int updateDict(Dict Dict);

    /**
     * 删除字典
     *
     * @param DictId
     * @return
     */
    int deleteDict(int DictId);
}
