package cn.code4java.springbok.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.entity.Subject;
import cn.code4java.springbok.vo.SubjectVO;

/**
 * @ClassName SubjectApiService
 * @Description: 专题API服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface SubjectApiService extends IService<Subject> {
    /**
     * 根据id查询专题详情
     * @param id
     * @return
     */
    SubjectVO selectSubjectById(Integer id);
}
