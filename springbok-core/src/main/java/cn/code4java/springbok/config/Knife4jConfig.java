package cn.code4java.springbok.config;

import cn.code4java.springbok.constant.SpringbokConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    private static final String HEADER_NAME = "Springbok-Token";
    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components();
        //添加右上角的统一安全认证
        components.addSecuritySchemes(HEADER_NAME,
                new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .scheme("basic")
                        .name(HEADER_NAME)
                        .in(SecurityScheme.In.HEADER)
                        .description("请求头")
        );

        Contact contact = new Contact().name(SpringbokConstant.NAME).url(SpringbokConstant.URL).email(SpringbokConstant.EMAIL);
        Info info = new Info().title("Springbok接口文档").description("Springbok接口文档").version(SpringbokConstant.VERSION).contact(contact);
        return new OpenAPI().components(components).info(info);
    }
}
