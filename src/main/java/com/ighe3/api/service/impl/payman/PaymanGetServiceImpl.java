package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.provider.response.PaymanReportResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.PaymanGetService;
import com.ighe3.api.utils.Urls;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class PaymanGetServiceImpl implements PaymanGetService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanGetServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanReportResponse getReport(String paymanId) throws BaseException {
        String url = urls.getReportUrl() + "/" + paymanId;
        Request request = httpService.createRequest(url, httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, PaymanGetServiceImpl.class);
        return (PaymanReportResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanReportResponse.class);
    }
}
