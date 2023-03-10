package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.input.PaymanListIto;
import com.ighe3.api.model.PaymanListRequestFilter;
import com.ighe3.api.model.response.PaymanListResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.model.request.PaymanListRequest;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanListService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public PaymanListService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanListResponse getList(PaymanListIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getPaymanListUrl(), createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanListResponse paymanResponseBody
                = (PaymanListResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanListResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(PaymanListIto inputDto) throws Exception {

        PaymanListRequestFilter filter = getPaymanRequestFilterObject(inputDto.getFilter());
        PaymanListRequest requestBodyObject = getRequestBodyObject(inputDto, filter);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    // TODO: about input parameter: either this function is useless, or request object must be
    // customized for input transfer object.
    private PaymanListRequestFilter getPaymanRequestFilterObject(PaymanListRequestFilter filter) {
        PaymanListRequestFilter paymanListRequestFilter = new PaymanListRequestFilter();
        paymanListRequestFilter.setBankCode(filter.getBankCode());
        paymanListRequestFilter.setExpirationDateFrom(filter.getExpirationDateFrom());
        paymanListRequestFilter.setExpirationDateTo(filter.getExpirationDateTo());
        paymanListRequestFilter.setMaxDailyTransactionsCountFrom(filter.getMaxDailyTransactionsCountFrom());
        paymanListRequestFilter.setMaxDailyTransactionsCountTo(filter.getMaxDailyTransactionsCountTo());
        paymanListRequestFilter.setMaxMonthlyTransactionsCountFrom(filter.getMaxMonthlyTransactionsCountFrom());
        paymanListRequestFilter.setMaxMonthlyTransactionsCountTo(filter.getMaxMonthlyTransactionsCountTo());
        paymanListRequestFilter.setStartDateFrom(filter.getStartDateFrom());
        paymanListRequestFilter.setStartDateTo(filter.getStartDateTo());
        paymanListRequestFilter.setStatuses(filter.getStatuses());
        paymanListRequestFilter.setTransactionMaxAmountFrom(filter.getTransactionMaxAmountFrom());
        paymanListRequestFilter.setTransactionMaxAmountTo(filter.getTransactionMaxAmountTo());
        paymanListRequestFilter.setPaymanId(filter.getPaymanId());
        paymanListRequestFilter.setUserIds(filter.getUserIds());
        paymanListRequestFilter.setPermissionIds(filter.getPermissionIds());
        return paymanListRequestFilter;
    }

    private PaymanListRequest getRequestBodyObject(PaymanListIto inputDto, PaymanListRequestFilter filter) {
        PaymanListRequest requestBodyObject = new PaymanListRequest();
        requestBodyObject.setFilter(filter);
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
