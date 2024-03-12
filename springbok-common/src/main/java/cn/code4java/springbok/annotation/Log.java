package cn.code4java.springbok.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Log
 * @Description: 日志注解
 * @Author fengwensheng
 * @Date 2024/1/4
 * @Version V1.0
 **/
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 日志标题
     */
    String title() default "";
}
