package cn.code4java.springbok.service.api;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.Subject;
import cn.code4java.springbok.mapper.SubjectItemSaleMapper;
import cn.code4java.springbok.mapper.SubjectMapper;
import cn.code4java.springbok.vo.SubjectItemSaleVO;
import cn.code4java.springbok.vo.SubjectVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SubjectApiServiceImpl
 * @Description: 专题API服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SubjectApiServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectApiService {

    private SubjectItemSaleMapper subjectItemSaleSkuMapper;

    /**
     * 根据id查询专题详情
     * @param id
     * @return
     */
    @Override
    public SubjectVO selectSubjectById(Integer id) {
        Subject subject = getById(id);
        SubjectVO subjectVO = BeanUtil.toBean(subject, SubjectVO.class);
        List<SubjectItemSaleVO> subjectItemSales = subjectItemSaleSkuMapper.selectSubjectItemSale(id);
        subjectVO.setSubjectItemSaleList(subjectItemSales);
        return subjectVO;
    }
}
