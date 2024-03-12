package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.MemberQueryDTO;
import cn.code4java.springbok.entity.Member;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @ClassName MemberService
 * @Description: 会员服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface MemberService {
    /**
     * 分页查询会员
     *
     * @param memberQueryDTO
     * @return
     */
    Page<Member> pageMember(MemberQueryDTO memberQueryDTO);

    /**
     * 根据id查询会员
     *
     * @param id
     * @return
     */
    Member selectMemberById(Integer id);
}
