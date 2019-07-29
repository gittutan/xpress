package com.wuyuncheng.xpress.config;

import com.wuyuncheng.xpress.filter.CorsFilter;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
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

    /**
     * 解决上传文件大于 10M 出现连接重置的问题
     * 此异常内容 GlobalException 也捕获不到
     */
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }

}
