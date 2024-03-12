package cn.code4java.springbok.service.api;

import cn.code4java.springbok.enums.LoginDriverEnum;
import cn.code4java.springbok.mapper.MemberMapper;
import cn.code4java.springbok.vo.api.MemberApiVO;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.Member;
import cn.code4java.springbok.enums.OSSDistrictEnum;
import cn.code4java.springbok.storage.StorageService;
import cn.code4java.springbok.storage.SystemStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName MemberApiServiceImpl
 * @Description: 会员API服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class MemberApiServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberApiService {

    private MemberMapper memberMapper;
    private StorageService storageService;

    /**
     * 微信登录
     *
     * @param phone
     * @return
     */
    @Override
    public MemberApiVO wxLogin(String phone) {
        Member member = memberMapper.selectOne(new LambdaQueryWrapper<Member>().eq(Member::getPhone, phone));
        if (member == null) {
            member = new Member();
            member.setNickname(phone);
            member.setUsername(phone);
            member.setPhone(phone);
            member.setGender("男");
            memberMapper.insert(member);
        }
        MemberApiVO memberApiVO = new MemberApiVO();
        BeanUtil.copyProperties(member, memberApiVO);
        StpUtil.login(member.getMemberId(), LoginDriverEnum.APP.name());
        memberApiVO.setToken(StpUtil.getTokenValue());
        return memberApiVO;
    }

    /**
     * 查询会员
     *
     * @return
     */
    @Override
    public MemberApiVO selectMember() {
        Member member = memberMapper.selectById(StpUtil.getLoginIdAsInt());
        return BeanUtil.toBean(member, MemberApiVO.class);
    }

    /**
     * 修改会员
     *
     * @param member
     * @return
     */
    @Override
    public int updateMember(Member member) {
        member.setMemberId(StpUtil.getLoginIdAsInt());
        return memberMapper.updateById(member);
    }

    /**
     * 上传会员头像
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String uploadAvatar(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        SystemStorage storage = storageService.store(inputStream, "image/png", UUID.randomUUID().toString() + ".png", OSSDistrictEnum.USER.getType());
        Member member = new Member();
        member.setMemberId(StpUtil.getLoginIdAsInt());
        member.setAvatar(storage.getUrl());
        memberMapper.updateById(member);
        return member.getAvatar();
    }
}
