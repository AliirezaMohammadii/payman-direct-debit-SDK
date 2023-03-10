package com.ighe3.api.service.payman;

import com.ighe3.api.dto.input.PaymanCreateIto;
import com.ighe3.api.dto.output.PaymanCreateOto;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.PaymanContract;
import com.ighe3.api.model.PaymanModel;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.model.request.PaymanCreateRequest;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.TraceIdGenerator;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateService extends BaseService {

    @Value("${callback.url}")
    private String callbackUrl;

    private final AccessTokenService accessTokenService;
    private final MerchantPermissionsService merchantPermissionsService;
    private final Urls urls;

    public CreateService(AccessTokenService accessTokenService, MerchantPermissionsService merchantPermissionsService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.merchantPermissionsService = merchantPermissionsService;
        this.urls = urls;
    }

    public PaymanCreateOto create(PaymanCreateIto inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getCreateUrl(), createHeaders(inputDto));
        BaseResponse paymanResponse = sendRequest(request);
        Headers headers = paymanResponse.getHeaders();
        PaymanCreateOto appResponse = new PaymanCreateOto(headers.get("Location"));
        return appResponse;
    }

    private RequestBody createRequestBody(PaymanCreateIto inputDto) throws RuntimeException {
        PaymanContract paymanContract = getContractObject(inputDto);
        PaymanModel paymanObject = getPaymanObject(paymanContract, inputDto);
        PaymanCreateRequest requestBody = getRequestBody(paymanObject);

        String json = GeneralUtils.convertJavaObjectToJson(requestBody);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanCreateRequest getRequestBody(PaymanModel paymanObject) {
        PaymanCreateRequest requestBodyObject = new PaymanCreateRequest();
        requestBodyObject.setTraceId(TraceIdGenerator.generate());
        // TODO: in fact, userId must be fetched from database.
        requestBodyObject.setRedirectUrl(callbackUrl + "?userId=" + paymanObject.getUserId());
        requestBodyObject.setPayman(paymanObject);
        return requestBodyObject;
    }

    private PaymanModel getPaymanObject(PaymanContract paymanContract, PaymanCreateIto inputDto) throws RuntimeException {
        PaymanModel paymanObject = new PaymanModel();
        paymanObject.setUserId(inputDto.getMobileNumber());
        paymanObject.setBankCode(inputDto.getBankCode());
        paymanObject.setPermissionIds(merchantPermissionsService.getPermissionIds());
        paymanObject.setContract(paymanContract);
        return paymanObject;
    }

    private PaymanContract getContractObject(PaymanCreateIto inputDto) {
        PaymanContract paymanContract = new PaymanContract();
        paymanContract.setStartDate(inputDto.getStartDate());
        paymanContract.setExpirationDate(inputDto.getExpirationDate());
        paymanContract.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        paymanContract.setMaxMonthlyTransactionCount(inputDto.getMaxMonthlyTransactionCount());
        paymanContract.setMaxTransactionAmount(inputDto.getMaxTransactionAmount());
        paymanContract.setDailyMaxTransactionAmount(inputDto.getDailyMaxTransactionAmount());
        return paymanContract;
    }

    private Headers createHeaders(PaymanCreateIto inputDto) throws RuntimeException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .add(RequestHeaderKeys.MOBILE_NO.getValue(), inputDto.getMobileNumber())
                .add(RequestHeaderKeys.NATIONAL_CODE.getValue(), inputDto.getNationalCode())
                .build();
        return headers;
    }

    @Override
    protected Headers createHeaders() throws RuntimeException {
        return null;
    }
}
