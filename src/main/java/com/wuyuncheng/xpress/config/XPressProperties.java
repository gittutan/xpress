package com.wuyuncheng.xpress.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@Data
@ConfigurationProperties(prefix = "xpress")
public class XPressProperties {

    /**
     * JWT secret.
     */
    private String jwtSecret = UUID.randomUUID().toString();

    /**
     * JWT expiration time(minute).
     */
    private long jwtTimeout = 30;

    /**
     * JWT authorization header.
     */
    private String jwtHeader = "X-Token";

    /**
     * File upload path.
     */
    private String uploadPath = "D:\\upload\\";

}
