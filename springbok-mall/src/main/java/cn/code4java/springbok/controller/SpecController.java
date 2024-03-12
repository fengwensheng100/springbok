package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.SpecDTO;
import cn.code4java.springbok.dto.SpecQueryDTO;
import cn.code4java.springbok.entity.Spec;
import cn.code4java.springbok.service.SpecService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SpecController
 * @Description: 规格控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "规格管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/spec")
public class SpecController {

    private final SpecService propertyService;

    /**
     * 分页查询规格信息
     *
     * @param params
     * @return
     */
    @GetMapping("/pageSpec")
    @Operation(summary = "分页查询规格信息", description = "分页查询规格信息")
    public BaseResponse pageSpec(SpecQueryDTO params) {
        return BaseResponse.success(propertyService.pageSpec(params));
    }

    /**
     * 查询规格信息列表
     *
     * @param spec
     * @return
     */
    @GetMapping("/listSpec")
    @Operation(summary = "查询规格信息列表", description = "查询规格信息列表")
    public BaseResponse listSpec(@RequestBody Spec spec) {
        return BaseResponse.success(propertyService.listSpec(spec));
    }

    /**
     * 根据id查询规格信息
     *
     * @param specId
     * @return
     */
    @GetMapping("/selectSpecById")
    @Operation(summary = "根据id查询规格信息", description = "根据id查询规格信息")
    public BaseResponse selectSpecById(Integer specId) {
        return BaseResponse.success(propertyService.selectSpecById(specId));
    }

    /**
     * 根据多个id查询规格信息
     *
     * @param ids
     * @return
     */
    @GetMapping("/selectSpecByIds")
    @Operation(summary = "根据多个id查询规格信息", description = "根据多个id查询规格信息")
    public BaseResponse selectSpecByIds(@RequestParam String ids) {
        return BaseResponse.success(propertyService.selectSpecByIds(ids));
    }

    /**
     * 新增规格信息
     *
     * @param specDTO
     * @return
     */
    @PostMapping("/addSpec")
    @Operation(summary = "新增规格信息", description = "新增规格信息")
    public BaseResponse addSpec(@RequestBody SpecDTO specDTO) {
        return BaseResponse.success(propertyService.addSpec(specDTO));
    }

    /**
     * 修改规格信息
     *
     * @param specDTO
     * @return
     */
    @PostMapping("/updateSpec")
    @Operation(summary = "修改规格信息", description = "修改规格信息")
    public BaseResponse updateSpec(@RequestBody SpecDTO specDTO) {
        return BaseResponse.success(propertyService.updateSpec(specDTO));
    }

    /**
     * 删除规格信息
     *
     * @param specId
     * @return
     */
    @PostMapping("/deleteSpec")
    @Operation(summary = "删除规格信息", description = "删除规格信息")
    public BaseResponse deleteSpec(Integer specId) {
        return BaseResponse.success(propertyService.deleteSpec(specId));
    }
}
