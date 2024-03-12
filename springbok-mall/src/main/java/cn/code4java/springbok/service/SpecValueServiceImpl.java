package cn.code4java.springbok.service;

import cn.code4java.springbok.entity.SpecValue;
import cn.code4java.springbok.mapper.SpecValueMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SpecValueServiceImpl
 * @Description: 规格值服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SpecValueServiceImpl extends ServiceImpl<SpecValueMapper, SpecValue> implements SpecValueService {

    private SpecValueMapper specValueMapper;

    /**
     * 根据规格id列表查询规格值
     * @param specValueIds
     * @return
     */
    @Override
    public List<SpecValue> listSpecValueBySpecIds(List<Integer> specValueIds) {
        return specValueMapper.selectList(new LambdaQueryWrapper<SpecValue>().in(SpecValue::getSpecId, specValueIds));
    }
}
