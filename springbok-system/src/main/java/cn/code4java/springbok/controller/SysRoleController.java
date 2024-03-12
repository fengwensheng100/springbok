package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.SysRoleDTO;
import cn.code4java.springbok.dto.SysRoleQueryDTO;
import cn.code4java.springbok.entity.SysRole;
import cn.code4java.springbok.service.SysRoleService;
import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.annotation.Log;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysRoleController
 * @Description: 角色控制器
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
@Tag(name = "角色管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    /**
     * 分页查询角色信息
     *
     * @param params
     * @return
     */
    @GetMapping("/pageSysRole")
    @Operation(summary = "分页查询角色信息", description = "分页查询角色信息")
    public BaseResponse pageSysRole(SysRoleQueryDTO params) {
        return BaseResponse.success(sysRoleService.pageSysRole(params));
    }

    /**
     * 查询角色信息列表
     *
     * @return
     */
    @GetMapping("/listSysRole")
    @Operation(summary = "查询角色信息列表", description = "查询角色信息列表")
    public BaseResponse listSysRole() {
        return BaseResponse.success(sysRoleService.listSysRole());
    }

    /**
     * 根据id查询角色信息
     *
     * @param sysRoleId
     * @return
     */
    @GetMapping("/selectSysRoleById")
    @Operation(summary = "根据id查询角色信息", description = "根据id查询角色信息")
    public BaseResponse selectSysRoleById(@Parameter(description = "角色id", required = true) Integer sysRoleId) {
        return BaseResponse.success(sysRoleService.selectSysRoleById(sysRoleId));
    }

    /**
     * 新增角色
     *
     * @param sysRoleDTO
     * @return
     */
    @Log(title = "新增角色")
    @PostMapping("/addSysRole")
    @Operation(summary = "新增角色", description = "新增角色")
    public BaseResponse addSysRole(@RequestBody SysRoleDTO sysRoleDTO) {
        return BaseResponse.success(sysRoleService.addSysRole(sysRoleDTO));
    }

    /**
     * 修改角色
     *
     * @param sysRoleDTO
     * @return
     */
    @Log(title = "修改角色")
    @PostMapping("/updateSysRole")
    @Operation(summary = "修改角色", description = "修改角色")
    public BaseResponse updateSysRole(@RequestBody SysRoleDTO sysRoleDTO) {
        return BaseResponse.success(sysRoleService.updateSysRole(sysRoleDTO));
    }

    /**
     * 删除角色
     *
     * @param sysRoleId
     * @return
     */
    @Log(title = "删除角色")
    @PostMapping("/deleteSysRole")
    @Operation(summary = "删除角色", description = "删除角色")
    public BaseResponse deleteSysRole(@Parameter(description = "角色id", required = true) Integer sysRoleId) {
        return BaseResponse.success(sysRoleService.deleteSysRole(sysRoleId));
    }
}
