package com.yiyue.lesson.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(urlPatterns = "/api/*",filterName = "myFilter")
//@Order(1)
public class MyFilter implements Filter {
    @Value("${open.url}")
    private String openurl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter 被初始化了");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter 被销毁了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        System.out.println("请求的接口URI="+uri+"请求的方式method="+method);
//        判断是否携带放行api
        PathMatcher pathMatcher = new AntPathMatcher();
        if(pathMatcher.match(openurl,uri)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            String token = httpServletRequest.getHeader("token");
            if(StringUtils.isEmpty(token)){
                servletRequest.getRequestDispatcher("/api/open/unLogin").forward(servletRequest,servletResponse);
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }


    }
}
