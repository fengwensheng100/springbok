package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.Dict;
import cn.code4java.springbok.entity.DictValue;
import lombok.Data;

import java.util.List;

/**
 * @ClassName DictVO
 * @Description: DictVO
 * @Author fengwensheng
 * @Date 2024/1/4
 * @Version V1.0
 **/
@Data
public class DictVO extends Dict {

    private List<DictValue> dictValueList;
}
