package cn.code4java.springbok.controller;

import cn.code4java.springbok.service.SysConfigService;
import cn.code4java.springbok.vo.BaseResponse;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.code4java.springbok.entity.SysConfig;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysConfigController
 * @Description: 系统配置控制器
 * @Author fengwensheng
 * @Date 2024/2/21
 * @Version V1.0
 **/
@Tag(name = "配置管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class SysConfigController {

    private final SysConfigService sysConfigService;

    /**
     * 查询系统配置列表
     *
     * @return
     */
    @GetMapping("/listSysConfig")
    @Operation(summary = "查询系统配置列表", description = "查询系统配置列表")
    public BaseResponse listSysConfig() {
        List<SysConfig> list = sysConfigService.list();
        if (CollectionUtil.isNotEmpty(list)) {
            list.stream().forEach(sysConfig -> {
                List<SysConfig.ConfigOptions> configOptionsList = JSONUtil.toList(sysConfig.getConfigOptions(), SysConfig.ConfigOptions.class);
                sysConfig.setConfigOptionsList(configOptionsList);
            });
        }
        return BaseResponse.success(list);
    }

    /**
     * 根据key查询系统配置
     *
     * @param configKey
     * @return
     */
    @SaIgnore
    @GetMapping("/selectSysConfigByKey")
    @Operation(summary = "根据key查询系统配置", description = "根据key查询系统配置")
    public BaseResponse selectSysConfigByKey(@Parameter(description = "配置key", required = true) String configKey) {
        return BaseResponse.success(sysConfigService.getOne(Wrappers.<SysConfig>lambdaQuery().eq(SysConfig::getConfigKey, configKey)));
    }

    /**
     * 修改系统配置
     *
     * @param sysConfig
     * @return
     */
    @PostMapping("/updateSysConfig")
    @Operation(summary = "修改系统配置", description = "修改系统配置")
    public BaseResponse updateSysConfig(@RequestBody SysConfig sysConfig) {
        sysConfigService.update(Wrappers.<SysConfig>lambdaUpdate().set(SysConfig::getConfigValue, sysConfig.getConfigValue())
                .eq(SysConfig::getConfigKey, sysConfig.getConfigKey()));
        return BaseResponse.success();
    }
}
