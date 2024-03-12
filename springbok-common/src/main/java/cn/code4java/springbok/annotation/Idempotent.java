package cn.code4java.springbok.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Idempotent
 * @Description: 幂等注解
 * @Author fengwensheng
 * @Date 2024/2/23
 * @Version V1.0
 **/
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    /**
     * 过期时间，单位：s，默认-1，即不设置过期时间
     */
    long expireTime() default -1L;

    /**
     * 提示消息
     */
    String message() default "请勿重复请求";
}
