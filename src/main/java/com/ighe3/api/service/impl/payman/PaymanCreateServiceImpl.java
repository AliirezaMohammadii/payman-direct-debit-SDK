package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.CreateRequest;
import com.ighe3.api.dto.client.response.CreateResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Contract;
import com.ighe3.api.dto.PaymanDetails;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanCreateRequest;
import com.ighe3.api.service.payman.PaymanCreateService;
import com.ighe3.api.utils.*;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymanCreateServiceImpl implements PaymanCreateService {

    @Value("${callback.url}")
    private String callbackUrl;

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;
    private final MerchantPermissionsServiceImpl merchantPermissionsService;

    public PaymanCreateServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService, MerchantPermissionsServiceImpl merchantPermissionsService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
        this.merchantPermissionsService = merchantPermissionsService;
    }

    @Override
    public CreateResponse create(CreateRequest inputDto) throws BaseException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getCreateUrl(), createHeaders(inputDto));
        Response paymanResponse = httpService.sendRequest(request, PaymanCreateServiceImpl.class);
        Headers headers = paymanResponse.getHeaders();
        return new CreateResponse(headers.get("Location"));
    }

    private RequestBody createRequestBody(CreateRequest inputDto) throws RuntimeException {
        Contract contract = getContractObject(inputDto);
        PaymanDetails paymanDetails = getpaymanDetails(contract, inputDto);
        PaymanCreateRequest requestBody = getRequestBody(paymanDetails);
        String json = GeneralUtils.convertJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }

    private PaymanCreateRequest getRequestBody(PaymanDetails paymanDetails) {
        PaymanCreateRequest requestBodyObject = new PaymanCreateRequest();
        requestBodyObject.setTraceId(TraceIdGenerator.generate());
        // TODO: in fact, userId must be fetched from database.
        requestBodyObject.setRedirectUrl(callbackUrl + "?userId=" + paymanDetails.getUserId());
        requestBodyObject.setPayman(paymanDetails);
        return requestBodyObject;
    }

    private PaymanDetails getpaymanDetails(Contract contract, CreateRequest inputDto) throws RuntimeException {
        PaymanDetails paymanDetails = new PaymanDetails();
        paymanDetails.setUserId(inputDto.getMobileNumber());
        paymanDetails.setBankCode(inputDto.getBankCode());
        paymanDetails.setPermissionIds(merchantPermissionsService.getPermissionIds());
        paymanDetails.setContract(contract);
        return paymanDetails;
    }

    private Contract getContractObject(CreateRequest inputDto) {
        Contract contract = new Contract();
        contract.setStartDate(inputDto.getStartDate());
        contract.setExpirationDate(inputDto.getExpirationDate());
        contract.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        contract.setMaxMonthlyTransactionCount(inputDto.getMaxMonthlyTransactionCount());
        contract.setMaxTransactionAmount(inputDto.getMaxTransactionAmount());
        contract.setDailyMaxTransactionAmount(inputDto.getDailyMaxTransactionAmount());
        return contract;
    }

    private Headers createHeaders(CreateRequest inputDto) throws RuntimeException {
        Headers generalHeaders = httpService.createHeaders(accessTokenService.getAccessToken());
        Headers headers = generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, inputDto.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, inputDto.getNationalCode())
                .build();
        return headers;
    }
}
