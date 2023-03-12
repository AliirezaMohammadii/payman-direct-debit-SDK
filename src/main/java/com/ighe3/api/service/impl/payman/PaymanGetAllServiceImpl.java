package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.ListRequest;
import com.ighe3.api.dto.ListRequestFilter;
import com.ighe3.api.dto.provider.response.PaymanListResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanListRequest;
import com.ighe3.api.service.payman.PaymanGetAllService;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanGetAllServiceImpl implements PaymanGetAllService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanGetAllServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanListResponse getList(ListRequest inputDto) throws BaseException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getPaymanListUrl(), httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, PaymanGetAllServiceImpl.class);
        return (PaymanListResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanListResponse.class);
    }

    private RequestBody createRequestBody(ListRequest inputDto) throws RuntimeException {
        ListRequestFilter filter = getPaymanRequestFilterObject(inputDto.getFilter());
        PaymanListRequest requestBodyObject = getRequestBodyObject(inputDto, filter);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
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
}
