package com.payman.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("header.value")
@Data
public class InternalRequestHeaderValuesPropertiesConfig {

    private String appDeviceId;
    private String appClientIpAddress;
    private String appClientPlatformType;
    private String appClientDeviceId;
    private String appClientUserId;
    private String appClientUserAgent;
}
