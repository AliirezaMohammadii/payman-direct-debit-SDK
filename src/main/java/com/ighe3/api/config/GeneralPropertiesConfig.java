package com.ighe3.api.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("general")
@Data
public class GeneralPropertiesConfig {

    private String onlyNormalPay;

    public String hasOnlyNormalPay() {
        return onlyNormalPay;
    }
}
