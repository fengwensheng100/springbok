package cn.code4java.springbok.config;

import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.vo.TokenSessionVO;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName MPMetaObjectHandler
 * @Description: 数据填充处理器
 * @Author fengwensheng
 * @Date 2024/1/3
 * @Version V1.0
 **/
@Component
public class MPMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        TokenSessionVO tokenSessionVO = (TokenSessionVO) StpUtil.getTokenSession().get(SpringbokConstant.TOKEN_SESSION_EXTRA);
        this.strictInsertFill(metaObject, "createdName", String.class, tokenSessionVO.getUsername());
        this.strictInsertFill(metaObject, "createdTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // mybatis-plus填充策略,默认有值不覆盖,如果提供的值为null也不填充
        // 这里不使用mybatis-plus提供的填充方式，直接赋值填充，解决有值时不覆盖的问题
        TokenSessionVO tokenSessionVO = (TokenSessionVO) StpUtil.getTokenSession().get(SpringbokConstant.TOKEN_SESSION_EXTRA);
        metaObject.setValue("updatedName", tokenSessionVO.getUsername());
        metaObject.setValue("updatedTime", new Date());
    }
}
