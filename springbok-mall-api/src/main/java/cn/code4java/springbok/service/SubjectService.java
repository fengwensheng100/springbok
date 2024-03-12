package cn.code4java.springbok.service;

import cn.code4java.springbok.entity.Subject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.SubjectDTO;
import cn.code4java.springbok.dto.SubjectQueryDTO;
import cn.code4java.springbok.vo.SubjectVO;

import java.util.List;

/**
 * @ClassName SubjectService
 * @Description 专题服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface SubjectService {
    /**
     * 分页查询专题
     *
     * @param subjectQueryDTO
     * @return
     */
    Page<Subject> pageSubject(SubjectQueryDTO subjectQueryDTO);

    /**
     * 查询专题列表
     *
     * @param subject
     * @return
     */
    List<Subject> listSubject(Subject subject);

    /**
     * 根据id查询专题
     *
     * @param id
     * @return
     */
    SubjectVO selectSubjectById(Integer id);

    /**
     * 新增专题
     *
     * @param subjectDTO
     * @return
     */
    boolean addSubject(SubjectDTO subjectDTO);

    /**
     * 修改专题
     *
     * @param subjectDTO
     * @return
     */
    boolean updateSubject(SubjectDTO subjectDTO);

    /**
     * 删除专题
     *
     * @param id
     * @return
     */
    boolean deleteSubject(Integer id);
}
