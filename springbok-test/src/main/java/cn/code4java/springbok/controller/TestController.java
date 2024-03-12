package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.TestQueryDTO;
import cn.code4java.springbok.service.TestService;
import cn.code4java.springbok.vo.BaseResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestContoller
 * @Description: TestController
 * @Author fengwensheng
 * @Date 2024/3/3
 * @Version V1.0
 **/
@Tag(name = "测试管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    /**
     * 测试分页查询
     *
     * @param params
     * @return
     */
    @GetMapping("/pageTest")
    @Operation(summary = "测试分页查询", description = "测试分页查询")
    public BaseResponse pageTest(TestQueryDTO params) {
        Page page = new Page();
        page.setCurrent(params.getCurrent());
        page.setSize(params.getSize());
        return BaseResponse.success(testService.page(page));
    }
}
