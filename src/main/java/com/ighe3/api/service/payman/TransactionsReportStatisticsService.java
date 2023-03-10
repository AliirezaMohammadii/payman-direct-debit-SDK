package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportStatisticsResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsReportStatisticsRequest;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsReportStatisticsService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TransactionsReportStatisticsService(ExceptionTranslator exceptionTranslator,
                                               AccessTokenService accessTokenService,
                                               Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTransactionsReportStatisticsResponse getReportStatistics(TransactionsReportStatisticsRequest inputDto)
            throws RuntimeException {

        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getTransactionsReportStatisticsUrl(), createHeaders());
        Response paymanResponse = sendRequest(request, TransactionsReportStatisticsService.class);
        PaymanTransactionsReportStatisticsResponse paymanResponseBody
                = (PaymanTransactionsReportStatisticsResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsReportStatisticsResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(TransactionsReportStatisticsRequest inputDto) throws RuntimeException {

        PaymanTransactionsReportStatisticsRequest requestBodyObject = getRequestBodyObject(inputDto);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanTransactionsReportStatisticsRequest getRequestBodyObject(TransactionsReportStatisticsRequest inputDto) {
        PaymanTransactionsReportStatisticsRequest requestBodyObject
                = new PaymanTransactionsReportStatisticsRequest();
        requestBodyObject.setStartDate(inputDto.getStartDate());
        requestBodyObject.setEndDate(inputDto.getEndDate());
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws RuntimeException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }
}
