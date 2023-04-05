package com.ighe3.api.config;


import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Data
public class CredentialsPropertiesConfig {

    private String appKey;
    private String accessToken;
}
