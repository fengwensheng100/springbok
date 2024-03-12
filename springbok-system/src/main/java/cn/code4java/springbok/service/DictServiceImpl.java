package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.DictQueryDTO;
import cn.code4java.springbok.entity.Dict;
import cn.code4java.springbok.entity.DictValue;
import cn.code4java.springbok.vo.DictVO;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.DictMapper;
import cn.code4java.springbok.mapper.DictValueMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName DictServiceImpl
 * @Description: 字典服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    private DictMapper dictMapper;
    private DictValueMapper dictValueMapper;

    /**
     * 分页查询字典
     *
     * @param dictQueryDTO
     * @return
     */
    @Override
    public Page<Dict> pageDict(DictQueryDTO dictQueryDTO) {
        Page<Dict> page = new Page<>();
        page.setCurrent(dictQueryDTO.getCurrent());
        page.setSize(dictQueryDTO.getSize());
        return dictMapper.pageDict(page, dictQueryDTO);
    }

    /**
     * 查询字典列表
     *
     * @param dict
     * @return
     */
    @Override
    public List<DictVO> listDict(Dict dict) {
        List<Dict> dictList = dictMapper.selectList(Wrappers.lambdaQuery());
        if (CollectionUtil.isEmpty(dictList)) {
            return null;
        }
        List<DictVO> dictVOList = new LinkedList<>();
        dictList.stream().forEach(d -> {
            DictVO dictVO = new DictVO();
            BeanUtils.copyProperties(d, dictVO);
            List<DictValue> dictValueList = dictValueMapper.selectList(Wrappers.<DictValue>lambdaQuery().eq(DictValue::getDictId, d.getDictId()));
            dictVO.setDictValueList(dictValueList);
            dictVOList.add(dictVO);
        });
        return dictVOList;
    }

    /**
     * 新增字典
     *
     * @param dict
     * @return
     */
    @Override
    @Transactional
    public int addDict(Dict dict) {
        return dictMapper.insert(dict);
    }

    /**
     * 修改字典
     *
     * @param dict
     * @return
     */
    @Override
    public int updateDict(Dict dict) {
        Dict findDict = dictMapper.selectById(dict.getDictId());
        if (findDict.getDictType() == 1) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_ERROR, "系统字典不可被修改");
        }
        return dictMapper.updateById(dict);
    }

    /**
     * 删除字典
     *
     * @param dictId
     * @return
     */
    @Override
    public int deleteDict(int dictId) {
        Dict dict = dictMapper.selectById(dictId);
        if (dict.getDictType() == 1) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_ERROR, "系统字典不可被删除");
        }
        dictValueMapper.delete(new LambdaQueryWrapper<DictValue>().eq(DictValue::getDictId, dictId));
        return dictMapper.deleteById(dictId);
    }
}
