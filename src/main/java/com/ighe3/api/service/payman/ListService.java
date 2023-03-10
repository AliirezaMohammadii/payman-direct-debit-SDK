package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.ListRequest;
import com.ighe3.api.dto.ListRequestFilter;
import com.ighe3.api.dto.provider.response.PaymanListResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.Response;
import com.ighe3.api.dto.provider.request.PaymanListRequest;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class ListService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public ListService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanListResponse getList(ListRequest inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getPaymanListUrl(), createHeaders());
        Response paymanResponse = sendRequest(request, ListService.class);
        PaymanListResponse paymanResponseBody
                = (PaymanListResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanListResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(ListRequest inputDto) throws RuntimeException {

        ListRequestFilter filter = getPaymanRequestFilterObject(inputDto.getFilter());
        PaymanListRequest requestBodyObject = getRequestBodyObject(inputDto, filter);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    // TODO: about input parameter: either this function is useless, or request object must be
    // customized for input transfer object.
    private ListRequestFilter getPaymanRequestFilterObject(ListRequestFilter filter) {
        ListRequestFilter listRequestFilter = new ListRequestFilter();
        listRequestFilter.setBankCode(filter.getBankCode());
        listRequestFilter.setExpirationDateFrom(filter.getExpirationDateFrom());
        listRequestFilter.setExpirationDateTo(filter.getExpirationDateTo());
        listRequestFilter.setMaxDailyTransactionsCountFrom(filter.getMaxDailyTransactionsCountFrom());
        listRequestFilter.setMaxDailyTransactionsCountTo(filter.getMaxDailyTransactionsCountTo());
        listRequestFilter.setMaxMonthlyTransactionsCountFrom(filter.getMaxMonthlyTransactionsCountFrom());
        listRequestFilter.setMaxMonthlyTransactionsCountTo(filter.getMaxMonthlyTransactionsCountTo());
        listRequestFilter.setStartDateFrom(filter.getStartDateFrom());
        listRequestFilter.setStartDateTo(filter.getStartDateTo());
        listRequestFilter.setStatuses(filter.getStatuses());
        listRequestFilter.setTransactionMaxAmountFrom(filter.getTransactionMaxAmountFrom());
        listRequestFilter.setTransactionMaxAmountTo(filter.getTransactionMaxAmountTo());
        listRequestFilter.setPaymanId(filter.getPaymanId());
        listRequestFilter.setUserIds(filter.getUserIds());
        listRequestFilter.setPermissionIds(filter.getPermissionIds());
        return listRequestFilter;
    }

    private PaymanListRequest getRequestBodyObject(ListRequest inputDto, ListRequestFilter filter) {
        PaymanListRequest requestBodyObject = new PaymanListRequest();
        requestBodyObject.setFilter(filter);
        requestBodyObject.setLength(inputDto.getLength());
        requestBodyObject.setOffset(inputDto.getOffset());
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
