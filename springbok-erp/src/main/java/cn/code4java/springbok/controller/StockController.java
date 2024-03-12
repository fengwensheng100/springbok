package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.StockDTO;
import cn.code4java.springbok.dto.StockLineQueryDTO;
import cn.code4java.springbok.dto.StockQueryDTO;
import cn.code4java.springbok.service.StockService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StockController
 * @Description: 库存控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "库存管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    /**
     * 分页查询库存信息
     *
     * @param stockQueryDTO
     * @return
     */
    @GetMapping("/pageStock")
    @Operation(summary = "分页查询库存信息", description = "分页查询库存信息")
    public BaseResponse pageStock(StockQueryDTO stockQueryDTO) {
        return BaseResponse.success(stockService.pageStock(stockQueryDTO));
    }

    /**
     * 查询库存信息列表
     *
     * @param stockDTO
     * @return
     */
    @GetMapping("/listStock")
    @Operation(summary = "查询库存信息列表", description = "查询库存信息列表")
    public BaseResponse listStock(@RequestBody StockDTO stockDTO) {
        return BaseResponse.success(stockService.listStock(stockDTO));
    }

    /**
     * 根据id查询库存信息
     *
     * @param stockId
     * @return
     */
    @GetMapping("/selectStockById")
    @Operation(summary = "根据id查询库存信息", description = "根据id查询库存信息")
    public BaseResponse selectStockById(Integer stockId) {
        return BaseResponse.success(stockService.selectStockById(stockId));
    }

    /**
     * 分页查询库存明细
     *
     * @param stockLineQueryDTO
     * @return
     */
    @GetMapping("/pageStockLine")
    @Operation(summary = "分页查询库存明细", description = "分页查询库存明细")
    public BaseResponse pageStockLine(StockLineQueryDTO stockLineQueryDTO) {
        return BaseResponse.success(stockService.pageStockLine(stockLineQueryDTO));
    }
}
