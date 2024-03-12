package cn.code4java.springbok.api;

import cn.code4java.springbok.service.api.SubjectApiService;
import cn.code4java.springbok.vo.BaseResponse;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.code4java.springbok.entity.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SubjectApi
 * @Description: 专题API
 * @Author fengwensheng
 * @Date 2024/1/18
 * @Version V1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectApi {

    private final SubjectApiService subjectApiService;

    /**
     * 查询专题列表
     *
     * @param subject
     * @return
     */
    @GetMapping("/listSubject")
    public BaseResponse listBanner(Subject subject) {
        return BaseResponse.success(subjectApiService.list(Wrappers.<Subject>lambdaQuery().eq(Subject::getStatus, 1)));
    }

    /**
     * 获取专题详情
     *
     * @param subjectId
     * @return
     */
    @GetMapping("/selectSubjectById")
    public BaseResponse selectSubjectById(Integer subjectId) {
        return BaseResponse.success(subjectApiService.selectSubjectById(subjectId));
    }
}
