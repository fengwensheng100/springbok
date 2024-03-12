package cn.code4java.springbok.service.api;

import cn.code4java.springbok.entity.Member;
import cn.code4java.springbok.vo.api.MemberApiVO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName MemberApiService
 * @Description: 会员API服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface MemberApiService {
    /**
     * 微信登录
     *
     * @param phone
     * @return
     */
    MemberApiVO wxLogin(String phone);

    /**
     * 查询会员
     *
     * @return
     */
    MemberApiVO selectMember();

    /**
     * 修改会员
     *
     * @param member
     * @return
     */
    int updateMember(Member member);

    /**
     * 上传会员头像
     *
     * @param file
     * @return
     * @throws IOException
     */
    String uploadAvatar(@RequestParam("file") MultipartFile file) throws IOException;
}
