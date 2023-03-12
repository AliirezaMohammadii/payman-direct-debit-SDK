package com.ighe3.api.service.payman;

import com.ighe3.api.dto.provider.response.PaymanGetPaymanIdResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class GetPaymanIdService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public GetPaymanIdService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public String getPaymanId(String paymanCode) throws RuntimeException {
        String url = urls.getPaymanIdUrl() + "?payman_code" + "=" + paymanCode;
        Request request = createRequest(url, createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = sendRequest(request, GetPaymanIdService.class);
        PaymanGetPaymanIdResponse paymanResponseBody
                = (PaymanGetPaymanIdResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanGetPaymanIdResponse.class);
        return paymanResponseBody.getPaymanId();
    }
}
