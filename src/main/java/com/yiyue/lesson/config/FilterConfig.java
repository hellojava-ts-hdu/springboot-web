package com.yiyue.lesson.config;


import com.yiyue.lesson.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration//配置注解类
public class FilterConfig {

    @Bean
    public MyFilter myFilter(){
        return new MyFilter();
    }

    @Bean
    public FilterRegistrationBean getFilterRegistration(MyFilter myFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(myFilter);
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setName("myfilter");
        return filterRegistrationBean;
    }
}
