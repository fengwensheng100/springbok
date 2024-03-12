package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.SpecDTO;
import cn.code4java.springbok.dto.SpecQueryDTO;
import cn.code4java.springbok.entity.Spec;
import cn.code4java.springbok.entity.SpecValue;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.SpecMapper;
import cn.code4java.springbok.mapper.SpecValueMapper;
import cn.code4java.springbok.vo.SpecVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SpecServiceImpl
 * @Description: 规格服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SpecServiceImpl extends ServiceImpl<SpecMapper, Spec> implements SpecService {

    private SpecMapper specMapper;
    private SpecValueMapper specValueMapper;

    /**
     * 分页查询规格
     *
     * @param specQueryDTO
     * @return
     */
    @Override
    public Page<Spec> pageSpec(SpecQueryDTO specQueryDTO) {
        Page<Spec> page = new Page<>();
        page.setCurrent(specQueryDTO.getCurrent());
        page.setSize(specQueryDTO.getSize());
        return specMapper.pageSpec(page, specQueryDTO);
    }

    /**
     * 查询规格列表
     *
     * @param spec
     * @return
     */
    @Override
    public List<Spec> listSpec(Spec spec) {
        LambdaQueryWrapper<Spec> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(spec.getSpecName()), Spec::getSpecName, spec.getSpecName());
        return specMapper.selectList(wrapper);
    }

    /**
     * 根据id查询规格
     *
     * @param specId
     * @return
     */
    @Override
    public SpecVO selectSpecById(int specId) {
        SpecVO specVO = new SpecVO();
        Spec spec = specMapper.selectById(specId);
        BeanUtil.copyProperties(spec, specVO);
        List<SpecValue> specValueList = specValueMapper.selectList(new LambdaQueryWrapper<SpecValue>().eq(SpecValue::getSpecId, specId));
        specVO.setSpecValueNameList(specValueList.stream().map(SpecValue::getSpecValueName).collect(Collectors.toList()));
        specVO.setSpecValueList(specValueList);
        return specVO;
    }

    /**
     * 根据多个id查询规格
     *
     * @param specIds
     * @return
     */
    @Override
    public List<SpecVO> selectSpecByIds(String specIds) {
        List<SpecVO> specVOList = new LinkedList<>();
        List<Spec> specList = specMapper.selectBatchIds(Arrays.asList(specIds.split(",")));
        if (CollectionUtil.isNotEmpty(specList)) {
            specList.stream().forEach(spec -> {
                SpecVO specVO = new SpecVO();
                BeanUtil.copyProperties(spec, specVO);
                List<SpecValue> specValueList = specValueMapper.selectList(new LambdaQueryWrapper<SpecValue>().eq(SpecValue::getSpecId, spec.getSpecId()));
                specVO.setSpecValueNameList(specValueList.stream().map(SpecValue::getSpecValueName).collect(Collectors.toList()));
                specVO.setSpecValueList(specValueList);
                specVOList.add(specVO);
            });
        }
        return specVOList;
    }

    /**
     * 新增规格
     *
     * @param specDTO
     * @return
     */
    @Override
    public int addSpec(SpecDTO specDTO) {
        if (CollectionUtil.isEmpty(specDTO.getSpecValueNameList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR.getMessage());
        }
        StringBuilder specValueNameStr = new StringBuilder();
        specDTO.getSpecValueNameList().stream().forEach(specValueName -> {
            specValueNameStr.append(specValueName + "|");
        });
        specDTO.setSkuStr(specValueNameStr.substring(0, specValueNameStr.length() - 1));
        specMapper.insert(specDTO);
        specDTO.getSpecValueNameList().stream().forEach(specValueName -> {
            SpecValue specValue = new SpecValue();
            specValue.setSpecValueName(specValueName);
            specValue.setSpecId(specDTO.getSpecId());
            specValueMapper.insert(specValue);
        });
        return 1;
    }

    /**
     * 修改规格
     *
     * @param specDTO
     * @return
     */
    @Override
    public int updateSpec(SpecDTO specDTO) {
        if (CollectionUtil.isEmpty(specDTO.getSpecValueNameList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR.getMessage());
        }
        specValueMapper.delete(new LambdaQueryWrapper<SpecValue>().eq(SpecValue::getSpecId, specDTO.getSpecId()));
        StringBuilder skuNameStr = new StringBuilder();
        specDTO.getSpecValueNameList().stream().forEach(skuName -> {
            skuNameStr.append(skuName + "|");
        });
        specDTO.setSkuStr(skuNameStr.substring(0, skuNameStr.length() - 1));
        specMapper.updateById(specDTO);
        specDTO.getSpecValueNameList().stream().forEach(specValueName -> {
            SpecValue specValue = new SpecValue();
            specValue.setSpecValueName(specValueName);
            specValue.setSpecId(specDTO.getSpecId());
            specValueMapper.insert(specValue);
        });
        return 1;
    }

    /**
     * 删除规格
     *
     * @param specId
     * @return
     */
    @Override
    public int deleteSpec(int specId) {
        specValueMapper.delete(new LambdaQueryWrapper<SpecValue>().eq(SpecValue::getSpecId, specId));
        return specMapper.deleteById(specId);
    }
}
