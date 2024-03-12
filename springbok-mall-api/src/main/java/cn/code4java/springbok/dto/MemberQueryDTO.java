package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Member;
import lombok.Data;

/**
 * @ClassName MemberQueryDTO
 * @Description: MemberQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class MemberQueryDTO extends Member {

    private long size;
    private long current;
}
