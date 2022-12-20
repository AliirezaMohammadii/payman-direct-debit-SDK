package com.ighe3.api.management;

import com.ighe3.api.dal.dto.input.PaymanCreateInputDTO;
import com.ighe3.api.dal.dto.input.PaymanTransactionsInputDTO;
import com.ighe3.api.model.ContractObject;
import com.ighe3.api.model.PaymanObject;
import com.ighe3.api.model.paymanRequestBodies.PaymanCreateRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
public class PaymanCreateManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public Response create(PaymanCreateInputDTO inputDto) throws IOException {
        RequestBody body = createRequestBody();
        Request request = getRequest(body, Urls.CREATE_PAYMAN.getValue(), getHeaders());
        Response response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() {

        ContractObject contractObject = getContractObject();
        PaymanObject paymanObject = getPaymanObject(contractObject);
        PaymanCreateRequestBodyObject requestBodyObject = getRequestBodyObject(paymanObject);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanCreateRequestBodyObject getRequestBodyObject(PaymanObject paymanObject) {
        PaymanCreateRequestBodyObject requestBodyObject = new PaymanCreateRequestBodyObject();
        requestBodyObject.setTraceId("7777");
        requestBodyObject.setRedirectUrl("http://localhost");
        requestBodyObject.setPaymanObject(paymanObject);
        return requestBodyObject;
    }

    private PaymanObject getPaymanObject(ContractObject contractObject) {
        PaymanObject paymanObject = new PaymanObject();
        paymanObject.setUserId("1");
        paymanObject.setBankCode("BOOMIR");
        paymanObject.setPermissionIds(Collections.singletonList(1));
        paymanObject.setContractObject(contractObject);
        return paymanObject;
    }

    private ContractObject getContractObject() {
        ContractObject contractObject = new ContractObject();
        contractObject.setStartDate("2022-12-20T08:28:09.5662061+04:30");
        contractObject.setExpirationDate("2022-12-30T08:28:09.5662061+04:30");
        contractObject.setMaxDailyTransactionCount(10);
        contractObject.setMaxMonthlyTransactionCount(30);
        contractObject.setMaxTransactionAmount(200_000D);
        contractObject.setDailyMaxTransactionAmount(2_000_000D);
        return contractObject;
    }

    @Override
    protected Headers getHeaders() throws IOException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX +
                        paymanService.getAccessToken())
                .add(RequestHeaderKeys.MOBILE_NO.getValue(), "09120000000")
                .add(RequestHeaderKeys.NATIONAL_CODE.getValue(), "0123456789")
                .build();
        return headers;
    }
}
