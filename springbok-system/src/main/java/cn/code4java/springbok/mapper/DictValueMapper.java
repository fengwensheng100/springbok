package cn.code4java.springbok.mapper;

import cn.code4java.springbok.dto.DictValueQueryDTO;
import cn.code4java.springbok.entity.DictValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName DictValueMapper
 * @Description: DictValueMapper
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
public interface DictValueMapper extends BaseMapper<DictValue> {
    Page<DictValue> pageDictValue(Page page, @Param(value = "query") DictValueQueryDTO dictValueQueryDTO);
}
