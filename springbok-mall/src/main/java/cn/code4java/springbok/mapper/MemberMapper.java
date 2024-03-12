package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.MemberQueryDTO;
import cn.code4java.springbok.entity.Member;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName MemberMapper
 * @Description: MemberMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface MemberMapper extends BaseMapper<Member> {
    Page<Member> pageMember(Page page, @Param(value = "query") MemberQueryDTO memberQueryDTO);
}
