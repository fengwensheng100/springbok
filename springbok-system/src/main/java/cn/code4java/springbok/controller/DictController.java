package cn.code4java.springbok.controller;

import cn.code4java.springbok.entity.Dict;
import cn.code4java.springbok.service.DictService;
import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.annotation.Log;
import cn.code4java.springbok.dto.DictQueryDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DictController
 * @Description: 字典控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "字典管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict")
public class DictController {

    private final DictService dictService;

    /**
     * 分页查询字典
     *
     * @param params
     * @return
     */
    @GetMapping("/pageDict")
    @Operation(summary = "分页查询字典", description = "分页查询字典")
    public BaseResponse pageDict(DictQueryDTO params) {
        return BaseResponse.success(dictService.pageDict(params));
    }

    /**
     * 新增字典
     *
     * @param dict
     * @return
     */
    @Log(title = "新增字典")
    @PostMapping("/addDict")
    @Operation(summary = "新增字典", description = "新增字典")
    public BaseResponse addDict(@RequestBody Dict dict) {
        return BaseResponse.success(dictService.addDict(dict));
    }

    /**
     * 修改字典
     *
     * @param dict
     * @return
     */
    @Log(title = "修改字典")
    @PostMapping("/updateDict")
    @Operation(summary = "修改字典", description = "修改字典")
    public BaseResponse updateDict(@RequestBody Dict dict) {
        return BaseResponse.success(dictService.updateDict(dict));
    }

    /**
     * 删除字典
     *
     * @param dictId
     * @return
     */
    @Log(title = "删除字典")
    @PostMapping("/deleteDict")
    @Operation(summary = "删除字典", description = "删除字典")
    public BaseResponse deleteDict(@Parameter(description = "字典id", required = true) Integer dictId) {
        return BaseResponse.success(dictService.deleteDict(dictId));
    }
}
