package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.SubjectDTO;
import cn.code4java.springbok.dto.SubjectQueryDTO;
import cn.code4java.springbok.entity.Subject;
import cn.code4java.springbok.service.SubjectService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SubjectController
 * @Description: 专题控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "专题管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    /**
     * 分页查询专题
     *
     * @param params
     * @return
     */
    @GetMapping("/pageSubject")
    @Operation(summary = "分页查询专题", description = "分页查询专题")
    public BaseResponse pageSubject(SubjectQueryDTO params) {
        return BaseResponse.success(subjectService.pageSubject(params));
    }

    /**
     * 查询专题列表
     *
     * @param subject
     * @return
     */
    @GetMapping("/listSubject")
    @Operation(summary = "查询专题列表", description = "查询专题列表")
    public BaseResponse listSubject(@RequestBody Subject subject) {
        return BaseResponse.success(subjectService.listSubject(subject));
    }

    /**
     * 根据id查询专题
     *
     * @param subjectId
     * @return
     */
    @GetMapping("/selectSubjectById")
    @Operation(summary = "根据id查询专题", description = "根据id查询专题")
    public BaseResponse selectSubjectById(Integer subjectId) {
        return BaseResponse.success(subjectService.selectSubjectById(subjectId));
    }

    /**
     * 新增专题
     *
     * @param subjectDTO
     * @return
     */
    @PostMapping("/addSubject")
    @Operation(summary = "新增专题", description = "新增专题")
    public BaseResponse addSubject(@RequestBody SubjectDTO subjectDTO) {
        return BaseResponse.success(subjectService.addSubject(subjectDTO));
    }

    /**
     * 修改专题
     *
     * @param subjectDTO
     * @return
     */
    @PostMapping("/updateSubject")
    @Operation(summary = "修改专题", description = "修改专题")
    public BaseResponse updateSubject(@RequestBody SubjectDTO subjectDTO) {
        return BaseResponse.success(subjectService.updateSubject(subjectDTO));
    }

    /**
     * 删除专题
     *
     * @param subjectId
     * @return
     */
    @PostMapping("/deleteSubject")
    @Operation(summary = "删除专题", description = "删除专题")
    public BaseResponse deleteSubject(Integer subjectId) {
        return BaseResponse.success(subjectService.deleteSubject(subjectId));
    }
}
