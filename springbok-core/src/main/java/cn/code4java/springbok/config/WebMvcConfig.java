package cn.code4java.springbok.config;

import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.entity.SysMenu;
import cn.code4java.springbok.properties.AuthProperties;
import cn.code4java.springbok.service.SysMenuService;
import cn.code4java.springbok.storage.StorageProperties;
import cn.code4java.springbok.utils.RedisUtils;
import cn.code4java.springbok.utils.StringUtils;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @ClassName WebMvcConfig
 * @Description: WebMVC配置
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private StorageProperties storageProperties;
    private AuthProperties authProperties;
    private RedisUtils redisUtils;
    private SysMenuService sysMenuService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (this.storageProperties.getActive().equals("local")) {
            Path rootLocation = Paths.get(this.storageProperties.getLocal().getStoragePath());
            String localPath = "file:/" + rootLocation.toAbsolutePath().toString().replace("\\", "/") + "/";
            registry.addResourceHandler("/image/**").addResourceLocations(localPath);
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 放行OPTIONS类型的请求
            SaRouter.match(SaHttpMethod.OPTIONS).stop();
            // 所有请求都需要校验登录
            SaRouter.match("/**").check(r -> StpUtil.checkLogin());
            // 根据路由划分模块，不同模块不同鉴权
            List<SysMenu> sysMenus = redisUtils.getToBeanList(SpringbokConstant.KEY_SYSTEM_MENU, SysMenu.class);
            if (CollectionUtil.isEmpty(sysMenus)) {
                sysMenuService.refreshMenuCache();
            }
            sysMenus.stream().filter(sysMenu -> StringUtils.isNotBlank(sysMenu.getPermissionCode())).forEach(sysMenu -> {
                SaRouter.match("/" + sysMenu.getRouterName() + "/**")
                        .check(r -> StpUtil.checkPermission(sysMenu.getPermissionCode()))
                        .stop(); // 满足权限，退出匹配链
            });
        })).addPathPatterns("/**").excludePathPatterns(authProperties.getNotMatchUrls());
    }
}
