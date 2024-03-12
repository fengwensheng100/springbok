package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Tag;
import lombok.Data;

/**
 * @ClassName TagQueryDTO
 * @Description: TagQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class TagQueryDTO extends Tag {

    private long size;
    private long current;
}
