package com.ighe3.api.service.payman;

import com.ighe3.api.controller.dto.input.CreateInputDto;
import com.ighe3.api.controller.dto.output.CreateOutputDto;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.PaymanContract;
import com.ighe3.api.model.PaymanModel;
import com.ighe3.api.model.ResponseModel;
import com.ighe3.api.model.request.PaymanCreateRequest;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class CreateService extends BaseService {
    private final AccessTokenService accessTokenService;

    public CreateService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public CreateOutputDto create(CreateInputDto inputDto) throws Exception {
        ResponseModel paymanResponse = getResponseObject(inputDto);
        Headers headers = paymanResponse.getHeaders();
        CreateOutputDto appResponse = new CreateOutputDto(headers.get("Location"));
        return appResponse;
    }

    private ResponseModel getResponseObject(CreateInputDto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, Urls.CREATE.getValue(), createHeaders(inputDto));
        ResponseModel response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(CreateInputDto inputDto) throws Exception {
        PaymanContract paymanContract = getContractObject(inputDto);
        PaymanModel paymanObject = getPaymanObject(paymanContract, inputDto);
        PaymanCreateRequest requestBodyObject = getRequestBodyObject(paymanObject, inputDto);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanCreateRequest getRequestBodyObject(PaymanModel paymanObject, CreateInputDto inputDto) {
        PaymanCreateRequest requestBodyObject = new PaymanCreateRequest();
        requestBodyObject.setTraceId(inputDto.getTraceId());
        requestBodyObject.setRedirectUrl(inputDto.getRedirectUrl());
        requestBodyObject.setPaymanObject(paymanObject);
        return requestBodyObject;
    }

    private PaymanModel getPaymanObject(PaymanContract paymanContract, CreateInputDto inputDto) {
        PaymanModel paymanObject = new PaymanModel();
        paymanObject.setUserId(inputDto.getUserId());
        paymanObject.setBankCode(inputDto.getBankCode());
        paymanObject.setPermissionIds(inputDto.getPermissionIds());
        paymanObject.setPaymanContract(paymanContract);
        return paymanObject;
    }

    private PaymanContract getContractObject(CreateInputDto inputDto) {
        PaymanContract paymanContract = new PaymanContract();
        paymanContract.setStartDate(inputDto.getStartDate());
        paymanContract.setExpirationDate(inputDto.getExpirationDate());
        paymanContract.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        paymanContract.setMaxMonthlyTransactionCount(inputDto.getMaxMonthlyTransactionCount());
        paymanContract.setMaxTransactionAmount(inputDto.getMaxTransactionAmount());
        paymanContract.setCurrency(inputDto.getCurrency());
        return paymanContract;
    }

    protected Headers createHeaders(CreateInputDto inputDto) throws Exception {
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
    protected Headers createHeaders() throws Exception {
        return null;
    }

    @Override
    protected Object convertJsonToJavaObject(String value) {
        return null;
    }
}
