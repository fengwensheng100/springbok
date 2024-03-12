package cn.code4java.springbok.service;

import cn.code4java.springbok.entity.Dict;
import cn.code4java.springbok.entity.DictValue;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.dto.DictValueQueryDTO;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.DictMapper;
import cn.code4java.springbok.mapper.DictValueMapper;
import cn.code4java.springbok.utils.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName DictValueServiceImpl
 * @Description: 字典值服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class DictValueServiceImpl extends ServiceImpl<DictValueMapper, DictValue> implements DictValueService {

    private DictMapper dictMapper;
    private DictValueMapper dictValueMapper;
    private RedisUtils redisUtils;

    /**
     * 分页查询字典值
     *
     * @param dictValueQueryDTO
     * @return
     */
    @Override
    public Page<DictValue> pageDictValue(DictValueQueryDTO dictValueQueryDTO) {
        Page<DictValue> page = new Page<>();
        page.setCurrent(dictValueQueryDTO.getCurrent());
        page.setSize(dictValueQueryDTO.getSize());
        return dictValueMapper.pageDictValue(page, dictValueQueryDTO);
    }

    /**
     * 新增字典值
     *
     * @param dictValue
     * @return
     */
    @Override
    @Transactional
    public int addDictValue(DictValue dictValue) {
        dictValueMapper.insert(dictValue);
        this.freshDictCache(dictValue.getDictId());
        return 1;
    }

    /**
     * 修改字典值
     *
     * @param dictValue
     * @return
     */
    @Override
    public int updateDictValue(DictValue dictValue) {
        dictValueMapper.updateById(dictValue);
        this.freshDictCache(dictValue.getDictId());
        return 1;
    }

    /**
     * 删除字典值
     *
     * @param dictValueId
     * @return
     */
    @Override
    public int deleteDictValue(int dictValueId) {
        DictValue dictValue = dictValueMapper.selectById(dictValueId);
        Dict dict = dictMapper.selectById(dictValue.getDictId());
        if (dict.getDictType() == 1) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_ERROR, "系统字典不可被删除");
        }
        dictValueMapper.deleteById(dictValueId);
        this.freshDictCache(dictValue.getDictId());
        return 1;
    }

    /**
     * 刷新字典缓存
     *
     * @param dictId
     */
    @Override
    public void freshDictCache(int dictId) {
        Dict dict = dictMapper.selectById(dictId);
        List<DictValue> dictValueList = dictValueMapper.selectList(Wrappers.<DictValue>lambdaQuery().eq(DictValue::getDictId, dictId));
        redisUtils.setHash(SpringbokConstant.KEY_SYSTEM_DICT, dict.getDictCode(), JSONUtil.toJsonStr(dictValueList));
    }
}
