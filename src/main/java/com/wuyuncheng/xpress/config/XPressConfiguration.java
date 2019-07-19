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
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsFilter = new FilterRegistrationBean<>();
        corsFilter.setFilter(new CorsFilter());
        corsFilter.setName("CorsFilter");
        corsFilter.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        corsFilter.addUrlPatterns("/api/*");
        return corsFilter;
    }

}
