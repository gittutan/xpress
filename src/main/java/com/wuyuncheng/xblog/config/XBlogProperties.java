package com.wuyuncheng.xblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@Data
@ConfigurationProperties(prefix = "xblog")
public class XBlogProperties {

    /**
     * The JWT secret is randomUUID.
     */
    private String jwtSecretKey = UUID.randomUUID().toString();

}
