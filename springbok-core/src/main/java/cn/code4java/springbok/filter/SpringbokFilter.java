package cn.code4java.springbok.filter;

import cn.code4java.springbok.wrapper.SpringbokRequestWrapper;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName SpringbokFilter
 * @Description: 请求过滤器
 * @Author fengwensheng
 * @Date 2024/1/4
 * @Version V1.0
 **/
@Component
public class SpringbokFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 使用自定义的包装类包装POST请求，可多次读取输入流
        if (request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())) {
            filterChain.doFilter(new SpringbokRequestWrapper(request), servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
