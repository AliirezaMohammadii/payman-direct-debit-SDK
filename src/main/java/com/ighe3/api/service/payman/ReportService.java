package com.ighe3.api.service.payman;

import com.ighe3.api.dto.provider.response.PaymanReportResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.Urls;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class ReportService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public ReportService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanReportResponse getReport(String paymanId) throws RuntimeException {
        String url = urls.getReportUrl() + "/" + paymanId;
        Request request = createRequest(url, createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = sendRequest(request, ReportService.class);
        PaymanReportResponse paymanResponseBody
                = (PaymanReportResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanReportResponse.class);
        return paymanResponseBody;
    }
}
