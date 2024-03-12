package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Spec;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SpecDTO
 * @Description: SpecDTO
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
public class SpecDTO extends Spec {

    private List<String> specValueNameList;
}
