package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.Spec;
import cn.code4java.springbok.entity.SpecValue;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SpecVO
 * @Description: SpecVO
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
public class SpecVO extends Spec {

    private List<String> specValueNameList;
    private List<SpecValue> specValueList;
}
