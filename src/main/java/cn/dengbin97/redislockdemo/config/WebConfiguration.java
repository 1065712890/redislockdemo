package cn.dengbin97.redislockdemo.config;

import cn.dengbin97.redislockdemo.interceptor.RequestIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: redislockdemo
 * @description: 配置类 注册拦截器
 * @author: dengbin
 * @create: 2018-11-19 15:36
 **/

@Component
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    RequestIdInterceptor requestIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIdInterceptor);
        super.addInterceptors(registry);
    }
}
