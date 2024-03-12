package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.TagQueryDTO;
import cn.code4java.springbok.entity.Tag;
import cn.code4java.springbok.service.TagService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TagController
 * @Description: 标签控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@io.swagger.v3.oas.annotations.tags.Tag(name = "标签管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    /**
     * 分页查询标签
     *
     * @param tagQueryDTO
     * @return
     */
    @GetMapping("/pageTag")
    @Operation(summary = "分页查询标签", description = "分页查询标签")
    public BaseResponse pageTag(TagQueryDTO tagQueryDTO) {
        return BaseResponse.success(tagService.pageTag(tagQueryDTO));
    }

    /**
     * 查询标签列表
     *
     * @param tag
     * @return
     */
    @GetMapping("/listTag")
    @Operation(summary = "查询标签列表", description = "查询标签列表")
    public BaseResponse listTag(Tag tag) {
        return BaseResponse.success(tagService.listTag(tag));
    }

    /**
     * 新增标签
     *
     * @param tag
     * @return
     */
    @PostMapping("/addTag")
    @Operation(summary = "新增标签", description = "新增标签")
    public BaseResponse addTag(@RequestBody Tag tag) {
        return BaseResponse.success(tagService.addTag(tag));
    }

    /**
     * 修改标签
     *
     * @param tag
     * @return
     */
    @PostMapping("/updateTag")
    @Operation(summary = "修改标签", description = "修改标签")
    public BaseResponse updateTag(@RequestBody Tag tag) {
        return BaseResponse.success(tagService.updateTag(tag));
    }

    /**
     * 删除标签
     *
     * @param tagId
     * @return
     */
    @PostMapping("/deleteTag")
    @Operation(summary = "删除标签", description = "删除标签")
    public BaseResponse deleteTag(Integer tagId) {
        return BaseResponse.success(tagService.deleteTag(tagId));
    }
}
