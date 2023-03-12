package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.TransactionsRequestFilter;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsRequest;
import com.ighe3.api.service.payman.TransactionsService;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public TransactionsServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanTransactionsResponse getTransactions(TransactionsRequest inputDto) throws BaseException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getTransactionsUrl(), httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, TransactionsServiceImpl.class);
        return (PaymanTransactionsResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsResponse.class);
    }

    private RequestBody createRequestBody(TransactionsRequest inputDto) throws RuntimeException {
        TransactionsRequestFilter filter = getPaymanRequestFilterObject(inputDto.getFilter());
        PaymanTransactionsRequest requestBodyObject = getRequestBodyObject(inputDto, filter);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }

    // TODO: about input parameter: either this function is useless, or request object must be
    // customized for input transfer object.
    private TransactionsRequestFilter getPaymanRequestFilterObject(TransactionsRequestFilter filter) {
        TransactionsRequestFilter transactionsRequestFilter = new TransactionsRequestFilter();
        transactionsRequestFilter.setPaymanIds(filter.getPaymanIds());
        transactionsRequestFilter.setUserIds(filter.getUserIds());
        transactionsRequestFilter.setTraceId(filter.getTraceId());
        transactionsRequestFilter.setReferenceId(filter.getReferenceId());
        transactionsRequestFilter.setTransactionType(filter.getTransactionType());
        transactionsRequestFilter.setFromTransactionAmount(filter.getFromTransactionAmount());
        transactionsRequestFilter.setToTransactionAmount(filter.getToTransactionAmount());
        transactionsRequestFilter.setFromTransactionDate(filter.getFromTransactionDate());
        transactionsRequestFilter.setToTransactionDate(filter.getToTransactionDate());
        transactionsRequestFilter.setNote(filter.getNote());
        transactionsRequestFilter.setSourceBankCode(filter.getSourceBankCode());
        transactionsRequestFilter.setDestinationBankCode(filter.getDestinationBankCode());
        transactionsRequestFilter.setPaymanStatuses(filter.getPaymanStatuses());
        transactionsRequestFilter.setTransactionStatuses(filter.getTransactionStatuses());
        return transactionsRequestFilter;
    }

    private PaymanTransactionsRequest getRequestBodyObject(TransactionsRequest inputDto,
                                                           TransactionsRequestFilter transactionsRequestFilter) {

        PaymanTransactionsRequest requestBodyObject = new PaymanTransactionsRequest();
        requestBodyObject.setFilter(transactionsRequestFilter);
        requestBodyObject.setLength(inputDto.getLength());
        requestBodyObject.setOffset(inputDto.getOffset());
        return requestBodyObject;
    }
}
