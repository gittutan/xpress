package com.wuyuncheng.xpress.config;

import com.wuyuncheng.xpress.filter.CorsFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;

@Configuration
@EnableConfigurationProperties(XPressProperties.class)
//@EnableAspectJAutoProxy // 开启 AspectJ
public class XPressConfiguration {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> frb = new FilterRegistrationBean<>();
        frb.setFilter(new CorsFilter());
        frb.setName("CorsFilter");
        frb.setOrder(Ordered.HIGHEST_PRECEDENCE);
        frb.addUrlPatterns("/api/*");
        return frb;
    }

}
