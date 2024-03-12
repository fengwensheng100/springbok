package cn.code4java.springbok.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ApiSecurity
 * @Description: Api安全注解
 * @Author fengwensheng
 * @Date 2024/2/23
 * @Version V1.0
 **/
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiSecurity {
    /**
     * 是否校验token
     */
    boolean checkLogin() default true;
}
