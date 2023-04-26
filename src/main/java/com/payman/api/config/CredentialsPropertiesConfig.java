package com.payman.api.config;


import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("credentials")
@Data
public class CredentialsPropertiesConfig {

    private String appKey;
    private String appSecret;
    private String accessToken;
}
