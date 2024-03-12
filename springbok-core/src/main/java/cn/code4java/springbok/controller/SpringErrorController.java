package cn.code4java.springbok.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SpringErrorController
 * @Description: 默认错误处理Controller
 * @Author fengwensheng
 * @Date 2024/1/4
 * @Version V1.0
 **/
@Controller
public class SpringErrorController implements ErrorController {

    /**
     * 过滤器中抛出的异常无法被RestControllerAdvice拦截
     * 默认转到此地址，在此接口抛出异常即可被RestControllerAdvice拦截
     * @param request
     * @throws Throwable
     */
    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws Throwable {
        if (request.getAttribute("javax.servlet.error.exception") != null) {
            throw (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
    }
}
