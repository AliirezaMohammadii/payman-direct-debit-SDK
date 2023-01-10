package com.ighe3.api.service;

import com.ighe3.api.dal.dto.payman.input.*;
import com.ighe3.api.management.payman.*;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.payman.responseBody.*;
import com.ighe3.api.util.GeneralUtils;
import okhttp3.Headers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymanService implements BaseService {
    private final PaymanGetAccessTokenManager paymanGetAccessTokenManager;
    private final PaymanCreateManager paymanCreateManager;
    private final PaymanGetPaymanIdManager paymanGetPaymanIdManager;
    private final PaymanTraceCreateManager paymanTraceCreateManager;
    private final PaymanPayManager paymanPayManager;
    private final PaymanTracePayManager paymanTracePayManager;
    private final PaymanUpdateManager paymanUpdateManager;
    private final PaymanChangeStatusManager paymanChangeStatusManager;
    private final PaymanReportManager paymanReportManager;
    private final PaymanTransactionsManager paymanTransactionsManager;
    private final PaymanListManager paymanListManager;
    private final PaymanMerchantPermissionsManager paymanMerchantPermissionsManager;
    private final PaymanTransactionsReportManager paymanTransactionsReportManager;
    private final PaymanTransactionsReportStatisticsManager paymanTransactionsReportStatisticsManager;

    public PaymanService(@Lazy PaymanGetAccessTokenManager paymanGetAccessTokenManager,
                         @Lazy PaymanCreateManager paymanCreateManager,
                         @Lazy PaymanGetPaymanIdManager paymanGetPaymanIdManager,
                         @Lazy PaymanTraceCreateManager paymanTraceCreateManager,
                         @Lazy PaymanPayManager paymanPayManager,
                         @Lazy PaymanTracePayManager paymanTracePayManager,
                         @Lazy PaymanUpdateManager paymanUpdateManager,
                         @Lazy PaymanChangeStatusManager paymanChangeStatusManager,
                         @Lazy PaymanReportManager paymanReportManager,
                         @Lazy PaymanTransactionsManager paymanTransactionsManager,
                         @Lazy PaymanListManager paymanListManager,
                         @Lazy PaymanMerchantPermissionsManager paymanMerchantPermissionsManager,
                         @Lazy PaymanTransactionsReportManager paymanTransactionsReportManager,
                         @Lazy PaymanTransactionsReportStatisticsManager paymanTransactionsReportStatisticsManager) {
        this.paymanGetAccessTokenManager = paymanGetAccessTokenManager;
        this.paymanCreateManager = paymanCreateManager;
        this.paymanGetPaymanIdManager = paymanGetPaymanIdManager;
        this.paymanTraceCreateManager = paymanTraceCreateManager;
        this.paymanPayManager = paymanPayManager;
        this.paymanTracePayManager = paymanTracePayManager;
        this.paymanUpdateManager = paymanUpdateManager;
        this.paymanChangeStatusManager = paymanChangeStatusManager;
        this.paymanReportManager = paymanReportManager;
        this.paymanTransactionsManager = paymanTransactionsManager;
        this.paymanListManager = paymanListManager;
        this.paymanMerchantPermissionsManager = paymanMerchantPermissionsManager;
        this.paymanTransactionsReportManager = paymanTransactionsReportManager;
        this.paymanTransactionsReportStatisticsManager = paymanTransactionsReportStatisticsManager;
    }

    public String getAccessToken() throws Exception {
        ResponseObject response = paymanGetAccessTokenManager.getAccessToken();
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        String accessToken = (String) body.get("access_token");
        return accessToken;
    }

    public String createPayman(PaymanCreateInputDTO inputDto) throws Exception {
        ResponseObject response = paymanCreateManager.create(inputDto);
        Headers headers = response.getHeaders();
        String redirectUrl = headers.get("Location");
        return redirectUrl;
    }

    public String getPaymanId(String paymanCode) throws Exception {
        ResponseObject response = paymanGetPaymanIdManager.getPaymanId(paymanCode);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return (String) body.get("payman_id");
    }

    public PaymanTraceCreateResponseBodyObject traceCreatePayman(String traceId) throws Exception {
        ResponseObject response = paymanTraceCreateManager.trace(traceId);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    public void pay(PaymanPayInputDTO inputDto) throws Exception {
        ResponseObject response = paymanPayManager.pay(inputDto);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
    }

    public PaymanTracePayResponseBodyObject tracePay(String traceId, String date) throws Exception {
        ResponseObject response = paymanTracePayManager.trace(traceId, date);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    public void updatePayman(PaymanUpdateInputDTO inputDto) throws Exception {
        ResponseObject response = paymanUpdateManager.update(inputDto);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
    }

    public PaymanChangeStatusResponseBodyObject changePaymanStatus(PaymanChangeStatusInputDTO inputDTO) throws Exception {
        ResponseObject response = paymanChangeStatusManager.changeStatus(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    public PaymanReportResponseBodyObject getReport(String paymanId) throws Exception {
        ResponseObject response = paymanReportManager.getReport(paymanId);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    public PaymanTransactionsResponseBodyObject getTransactions(PaymanTransactionsInputDTO inputDTO) throws Exception {
        ResponseObject response = paymanTransactionsManager.getTransactions(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    public PaymanListResponseBodyObject getList(PaymanListInputDTO inputDTO) throws Exception {
        ResponseObject response = paymanListManager.getList(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    public List<PaymanMerchantPermissionResponseBodyObject> getMerchantPermissions() throws Exception {
        ResponseObject response = paymanMerchantPermissionsManager.getList();
//        List<Map<String, Object>> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    public PaymanTransactionsReportResponseBodyObject getTransactionsReport(PaymanTransactionsReportInputDTO inputDTO) throws Exception {
        ResponseObject response = paymanTransactionsReportManager.getReport(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    public PaymanTransactionsReportStatisticsResponseBodyObject getTransactionsReportStatistics(PaymanTransactionsReportStatisticsInputDTO inputDTO) throws Exception {
        ResponseObject response = paymanTransactionsReportStatisticsManager.getStatistics(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }
}
