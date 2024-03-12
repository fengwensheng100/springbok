package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Spec;
import lombok.Data;

/**
 * @ClassName SpecQueryDTO
 * @Description: SpecQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class SpecQueryDTO extends Spec {

    private long size;
    private long current;
}
