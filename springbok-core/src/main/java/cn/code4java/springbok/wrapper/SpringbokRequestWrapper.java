package cn.code4java.springbok.wrapper;

import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName SpringbokRequestWrapper
 * @Description: SpringbokRequestWrapper
 * @Author fengwensheng
 * @Date 2024/1/4
 * @Version V1.0
 **/
public class SpringbokRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 把request中的inputStream存起来，多次读取
     */
    private final byte[] body;

    public SpringbokRequestWrapper(HttpServletRequest request) {
        super(request);
        body = ServletUtil.getBodyBytes(request);
    }

    /**
     * 重写Reader方法
     * @return
     * @throws IOException
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 重写getInputStream方法，读取保存的body
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}
