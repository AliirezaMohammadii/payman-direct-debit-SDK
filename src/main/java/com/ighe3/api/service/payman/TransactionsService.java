package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.TransactionsRequestFilter;
import com.ighe3.api.model.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsRequest;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TransactionsService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTransactionsResponse getTransactions(TransactionsRequest inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getTransactionsUrl(), createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = sendRequest(request, TransactionsService.class);
        PaymanTransactionsResponse paymanResponseBody
                = (PaymanTransactionsResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(TransactionsRequest inputDto) throws RuntimeException {

        TransactionsRequestFilter filter = getPaymanRequestFilterObject(inputDto.getFilter());
        PaymanTransactionsRequest requestBodyObject = getRequestBodyObject(inputDto, filter);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
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
