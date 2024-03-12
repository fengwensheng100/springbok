package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Subject;
import lombok.Data;

/**
 * @ClassName SubjectQueryDTO
 * @Description: SubjectQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class SubjectQueryDTO extends Subject {

    private long size;
    private long current;
}
