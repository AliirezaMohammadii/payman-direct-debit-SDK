package com.ighe3.api.utils;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Urls {

    @Value("${payman.url.base}" + "${payman.url.access-token}")
    private String accessTokenUrl;

    @Value("${payman.url.base}" + "${payman.url.create}")
    private String createUrl;

    @Value("${payman.url.base}" + "${payman.url.payman-id}")
    private String paymanIdUrl;

    @Value("${payman.url.base}" + "${payman.url.trace-create}")
    private String traceCreateUrl;

    @Value("${payman.url.base}" + "${payman.url.pay}")
    private String payUrl;

    @Value("${payman.url.base}" + "${payman.url.trace-pay}")
    private String tracePayUrl;

    @Value("${payman.url.base}" + "${payman.url.update}")
    private String updateUrl;

    @Value("${payman.url.base}" + "${payman.url.change-status}")
    private String changeStatusUrl;

    @Value("${payman.url.base}" + "${payman.url.report}")
    private String reportUrl;

    @Value("${payman.url.base}" + "${payman.url.transaction}")
    private String transactionsUrl;

    @Value("${payman.url.base}" + "${payman.url.list}")
    private String paymanListUrl;

    @Value("${payman.url.base}" + "${payman.url.merchant-permissions}")
    private String merchantPermissionsUrl;

    @Value("${payman.url.base}" + "${payman.url.transaction-report}")
    private String transactionsReportUrl;

    @Value("${payman.url.base}" + "${payman.url.transaction-report-statistics}")
    private String transactionsReportStatisticsUrl;
}
