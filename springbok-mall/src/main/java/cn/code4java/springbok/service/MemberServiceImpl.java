package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.MemberQueryDTO;
import cn.code4java.springbok.entity.Member;
import cn.code4java.springbok.mapper.MemberMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @ClassName MemberServiceImpl
 * @Description: 会员服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private MemberMapper memberMapper;

    /**
     * 分页查询会员
     *
     * @param couponQueryDTO
     * @return
     */
    @Override
    public Page<Member> pageMember(MemberQueryDTO couponQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(couponQueryDTO.getCurrent());
        page.setSize(couponQueryDTO.getSize());
        return memberMapper.pageMember(page, couponQueryDTO);
    }

    /**
     * 根据id查询会员
     *
     * @param id
     * @return
     */
    @Override
    public Member selectMemberById(Integer id) {
        return null;
    }
}
