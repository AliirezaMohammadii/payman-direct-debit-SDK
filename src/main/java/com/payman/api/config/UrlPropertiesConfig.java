package com.payman.api.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "payman.url")
@Data
public class UrlPropertiesConfig {

    private String base;
    private String accessToken;
    private String create;
    private String paymanId;
    private String traceCreation;
    private String pay;
    private String tracePayment;
    private String update;
    private String changeStatus;
    private String report;
    private String list;
    private String merchantPermissions;
    private String transactions;
    private String transactionsReport;
    private String transactionsStatisticReport;
}
