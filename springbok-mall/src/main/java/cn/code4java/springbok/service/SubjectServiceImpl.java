package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.SubjectDTO;
import cn.code4java.springbok.dto.SubjectQueryDTO;
import cn.code4java.springbok.entity.Subject;
import cn.code4java.springbok.entity.SubjectItemSale;
import cn.code4java.springbok.vo.SubjectVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.mapper.SubjectItemSaleMapper;
import cn.code4java.springbok.mapper.SubjectMapper;
import cn.code4java.springbok.vo.SubjectItemSaleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SubjectServiceImpl
 * @Description: 专题服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    private SubjectItemSaleMapper subjectItemSaleSkuMapper;

    /**
     * 分页查询专题
     *
     * @param subjectQueryDTO
     * @return
     */
    @Override
    public Page<Subject> pageSubject(SubjectQueryDTO subjectQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(subjectQueryDTO.getCurrent());
        page.setSize(subjectQueryDTO.getSize());
        return page(page, Wrappers.lambdaUpdate(subjectQueryDTO));
    }

    /**
     * 查询专题列表
     *
     * @param subject
     * @return
     */
    @Override
    public List<Subject> listSubject(Subject subject) {
        return list(Wrappers.lambdaQuery(subject));
    }

    /**
     * 根据id查询专题
     *
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

    /**
     * 新增专题
     *
     * @param subjectDTO
     * @return
     */
    @Override
    @Transactional
    public boolean addSubject(SubjectDTO subjectDTO) {
        save(subjectDTO);
        if (CollectionUtil.isNotEmpty(subjectDTO.getSubjectItemSaleList())) {
            subjectDTO.getSubjectItemSaleList().stream().forEach(subjectItemSale -> {
                subjectItemSale.setSubjectId(subjectDTO.getSubjectId());
                subjectItemSaleSkuMapper.insert(subjectItemSale);
            });
        }
        return true;
    }

    /**
     * 修改专题
     *
     * @param subjectDTO
     * @return
     */
    @Override
    @Transactional
    public boolean updateSubject(SubjectDTO subjectDTO) {
        subjectItemSaleSkuMapper.delete(Wrappers.<SubjectItemSale>lambdaQuery().eq(SubjectItemSale::getSubjectId, subjectDTO.getSubjectId()));
        if (CollectionUtil.isNotEmpty(subjectDTO.getSubjectItemSaleList())) {
            subjectDTO.getSubjectItemSaleList().stream().forEach(subjectItemSale -> {
                subjectItemSale.setSubjectId(subjectDTO.getSubjectId());
                subjectItemSaleSkuMapper.insert(subjectItemSale);
            });
        }
        return updateById(subjectDTO);
    }

    /**
     * 删除专题
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean deleteSubject(Integer id) {
        subjectItemSaleSkuMapper.delete(Wrappers.<SubjectItemSale>lambdaQuery().eq(SubjectItemSale::getSubjectId, id));
        return removeById(id);
    }
}
