package com.payman.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("general")
@Data
public class GeneralPropertiesConfig {

    private String onlyDirectDebit;

    public String hasOnlyDirectDebit() {
        return onlyDirectDebit;
    }
}
