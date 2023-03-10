package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.input.PaymanTransactionsIto;
import com.ighe3.api.model.response.PaymanTransactionsResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.PaymanTransactionsRequestFilter;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.model.request.PaymanTransactionsRequest;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TransactionsService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTransactionsResponse getTransactions(PaymanTransactionsIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getTransactionsUrl(), createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanTransactionsResponse paymanResponseBody
                = (PaymanTransactionsResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(PaymanTransactionsIto inputDto) throws Exception {

        PaymanTransactionsRequestFilter filter = getPaymanRequestFilterObject(inputDto.getFilter());
        PaymanTransactionsRequest requestBodyObject = getRequestBodyObject(inputDto, filter);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    // TODO: about input parameter: either this function is useless, or request object must be
    // customized for input transfer object.
    private PaymanTransactionsRequestFilter getPaymanRequestFilterObject(PaymanTransactionsRequestFilter filter) {
        PaymanTransactionsRequestFilter paymanTransactionsRequestFilter = new PaymanTransactionsRequestFilter();
        paymanTransactionsRequestFilter.setPaymanIds(filter.getPaymanIds());
        paymanTransactionsRequestFilter.setUserIds(filter.getUserIds());
        paymanTransactionsRequestFilter.setTraceId(filter.getTraceId());
        paymanTransactionsRequestFilter.setReferenceId(filter.getReferenceId());
        paymanTransactionsRequestFilter.setTransactionType(filter.getTransactionType());
        paymanTransactionsRequestFilter.setFromTransactionAmount(filter.getFromTransactionAmount());
        paymanTransactionsRequestFilter.setToTransactionAmount(filter.getToTransactionAmount());
        paymanTransactionsRequestFilter.setFromTransactionDate(filter.getFromTransactionDate());
        paymanTransactionsRequestFilter.setToTransactionDate(filter.getToTransactionDate());
        paymanTransactionsRequestFilter.setNote(filter.getNote());
        paymanTransactionsRequestFilter.setSourceBankCode(filter.getSourceBankCode());
        paymanTransactionsRequestFilter.setDestinationBankCode(filter.getDestinationBankCode());
        paymanTransactionsRequestFilter.setPaymanStatuses(filter.getPaymanStatuses());
        paymanTransactionsRequestFilter.setTransactionStatuses(filter.getTransactionStatuses());
        return paymanTransactionsRequestFilter;
    }

    private PaymanTransactionsRequest getRequestBodyObject(PaymanTransactionsIto inputDto,
                                                           PaymanTransactionsRequestFilter paymanTransactionsRequestFilter) {

        PaymanTransactionsRequest requestBodyObject = new PaymanTransactionsRequest();
        requestBodyObject.setFilter(paymanTransactionsRequestFilter);
        requestBodyObject.setLength(inputDto.getLength());
        requestBodyObject.setOffset(inputDto.getOffset());
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }
}
