package cn.code4java.springbok.controller;

import cn.code4java.springbok.service.IndexService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description: 首页控制器
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
@Tag(name = "首页接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/index")
public class IndexController {

    private final IndexService indexService;

    /**
     * 首页数据统计接口
     *
     * @return
     */
    @GetMapping("/statistics")
    @Operation(summary = "数据统计", description = "首页数据统计接口")
    public BaseResponse statistics() {
        return BaseResponse.success(indexService.statistics());
    }
}
