package cn.code4java.springbok.controller;

import cn.code4java.springbok.service.SpecValueService;
import cn.code4java.springbok.vo.BaseResponse;
import cn.hutool.core.convert.Convert;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @ClassName SpecValueController
 * @Description: 规格值控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "规格值管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/specValue")
public class SpecValueController {

    private final SpecValueService specValueService;

    /**
     * 根据规格id查询规格值列表
     *
     * @param specIds
     * @return
     */
    @GetMapping("/listSpecValueBySpecIds")
    @Operation(summary = "根据规格id查询规格值列表", description = "根据规格id查询规格值列表")
    public BaseResponse listSpecValueBySpecIds(String specIds) {
        return BaseResponse.success(specValueService.listSpecValueBySpecIds(Arrays.asList(Convert.toIntArray(specIds))));
    }
}
