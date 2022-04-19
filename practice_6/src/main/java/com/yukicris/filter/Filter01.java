package com.yukicris.filter;

import org.junit.Test;

import javax.servlet.*;
import java.io.IOException;

public class Filter01 implements Filter {
    // 初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("filter01以及初始化了");
    }

    //Chain: 链

    /**
     * 1 过滤器中所有代码,在过滤特定请求的时候都会执行
     * 2 必须要让过滤器继续通行
     *      chain.doFilter
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        System.out.println("Filter执行前");

        //让请求继续走,如果不写,到这里就被拦截了
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("Filter执行后");
    }

    //销毁
    @Override
    public void destroy() {
        System.gc();
        System.out.println("filter01已经销毁");
    }

}
