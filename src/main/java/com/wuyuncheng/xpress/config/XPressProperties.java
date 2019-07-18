package com.wuyuncheng.xpress.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@Data
@ConfigurationProperties(prefix = "xpress")
public class XPressProperties {

    /**
     * The JWT secret is randomUUID.
     */
    private String jwtSecretKey = UUID.randomUUID().toString();

}
