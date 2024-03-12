package cn.code4java.springbok.mapper;

import cn.code4java.springbok.dto.DictQueryDTO;
import cn.code4java.springbok.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName DictMapper
 * @Description: DictMapper
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
public interface DictMapper extends BaseMapper<Dict> {
    Page<Dict> pageDict(Page page, @Param(value = "query") DictQueryDTO dictQueryDTO);
}
