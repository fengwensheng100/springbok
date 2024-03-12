package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.dto.DictValueQueryDTO;
import cn.code4java.springbok.entity.DictValue;

/**
 * @ClassName DictValueService
 * @Description: 字典值服务类
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
public interface DictValueService extends IService<DictValue> {
    /**
     * 分页查询字典值
     *
     * @param dictValueQueryDTO
     * @return
     */
    Page<DictValue> pageDictValue(DictValueQueryDTO dictValueQueryDTO);

    /**
     * 新增字典值
     *
     * @param dictValue
     * @return
     */
    int addDictValue(DictValue dictValue);

    /**
     * 修改字典值
     *
     * @param dictValue
     * @return
     */
    int updateDictValue(DictValue dictValue);

    /**
     * 删除字典值
     *
     * @param dictValueId
     * @return
     */
    int deleteDictValue(int dictValueId);

    /**
     * 刷新字典缓存
     *
     * @param dictId
     */
    void freshDictCache(int dictId);
}
