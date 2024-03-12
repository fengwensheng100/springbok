package cn.code4java.springbok.controller;

import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.storage.StorageService;
import cn.code4java.springbok.storage.SystemStorage;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.util.UUID;

/**
 * @ClassName CommonController
 * @Description: 通用接口
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
@Tag(name = "通用接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
public class CommonController {

    private final StorageService storageService;

    @PostMapping("/uploadImg")
    @Operation(summary = "上传图片接口", description = "上传图片接口")
    public BaseResponse uploadPicture(@Parameter(description = "上传图片", required = true) @RequestParam("file") MultipartFile file,
                                      @Parameter(description = "类型", required = true) @RequestParam("type") int type) throws Exception {
        byte[] bytes = file.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        SystemStorage storage = storageService.store(inputStream, "image/png", UUID.randomUUID().toString() + ".png", type);
        return BaseResponse.success(storage.getUrl());
    }
}
