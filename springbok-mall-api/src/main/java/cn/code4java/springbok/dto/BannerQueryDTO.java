package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Banner;
import lombok.Data;

/**
 * @ClassName BannerQueryDTO
 * @Description: BannerQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class BannerQueryDTO extends Banner {

    private long size;
    private long current;
}
