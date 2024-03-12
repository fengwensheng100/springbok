package cn.code4java.springbok.controller;

import cn.code4java.springbok.entity.DictValue;
import cn.code4java.springbok.service.DictValueService;
import cn.code4java.springbok.vo.BaseResponse;
import cn.hutool.json.JSONUtil;
import cn.code4java.springbok.annotation.Log;
import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.dto.DictValueQueryDTO;
import cn.code4java.springbok.utils.RedisUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DictValueController
 * @Description: 字典值控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "字典值管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/dictValue")
public class DictValueController {

    private final DictValueService dictValueService;
    private final RedisUtils redisUtils;

    /**
     * 分页查询日志
     *
     * @param params
     * @return
     */
    @GetMapping("/pageDictValue")
    @Operation(summary = "分页查询日志", description = "分页查询日志")
    public BaseResponse pageDictValue(DictValueQueryDTO params) {
        return BaseResponse.success(dictValueService.pageDictValue(params));
    }

    /**
     * 根据字典编码查询字典值
     *
     * @param dictCode
     * @return
     */
    @GetMapping("/listDictValueByDictCode")
    @Operation(summary = "根据字典编码查询字典值", description = "根据字典编码查询字典值")
    public BaseResponse addDictValue(@Parameter(description = "字典编码", required = true) @RequestParam String dictCode) {
        Object dictValueCacheJson = redisUtils.getHash(SpringbokConstant.KEY_SYSTEM_DICT, dictCode);
        return BaseResponse.success(JSONUtil.toList(dictValueCacheJson.toString(), DictValue.class));
    }

    /**
     * 新增字典值
     *
     * @param dictValue
     * @return
     */
    @Log(title = "新增字典值")
    @PostMapping("/addDictValue")
    @Operation(summary = "新增字典值", description = "新增字典值")
    public BaseResponse addDictValue(@RequestBody DictValue dictValue) {
        return BaseResponse.success(dictValueService.addDictValue(dictValue));
    }

    /**
     * 修改字典值
     *
     * @param dictValue
     * @return
     */
    @Log(title = "修改字典值")
    @PostMapping("/updateDictValue")
    @Operation(summary = "修改字典值", description = "修改字典值")
    public BaseResponse updateDictValue(@RequestBody DictValue dictValue) {
        return BaseResponse.success(dictValueService.updateDictValue(dictValue));
    }

    /**
     * 删除字典值
     *
     * @param dictValueId
     * @return
     */
    @Log(title = "删除字典值")
    @PostMapping("/deleteDictValue")
    @Operation(summary = "删除字典值", description = "删除字典值")
    public BaseResponse deleteDictValue(@Parameter(description = "字典值id", required = true) Integer dictValueId) {
        return BaseResponse.success(dictValueService.deleteDictValue(dictValueId));
    }
}
