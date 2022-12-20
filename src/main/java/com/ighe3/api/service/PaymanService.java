package com.ighe3.api.service;

import com.ighe3.api.dal.dto.input.*;
import com.ighe3.api.management.*;
import com.ighe3.api.model.paymanResponseBodies.*;
import com.ighe3.api.util.GeneralUtils;
import okhttp3.Response;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public String getAccessToken() throws IOException {
        Response response = paymanGetAccessTokenManager.getAccessToken();
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return (String) body.get("access_token");
    }

    public void createPayman(PaymanCreateInputDTO inputDto) throws IOException {
        Response response = paymanCreateManager.create(inputDto);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
    }

    public String getPaymanId(String paymanCode) throws IOException {
        Response response = paymanGetPaymanIdManager.getPaymanId(paymanCode);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return (String) body.get("payman_id");
    }

    public PaymanTraceCreateResponseBodyObject traceCreatePayman(String traceId) throws IOException {
        Response response = paymanTraceCreateManager.trace(traceId);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }

    public void pay(PaymanPayInputDTO inputDto) throws IOException {
        Response response = paymanPayManager.pay(inputDto);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
    }

    public PaymanTracePayResponseBodyObject tracePay(String traceId, String date) throws IOException {
        Response response = paymanTracePayManager.trace(traceId, date);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }

    public void updatePayman(PaymanUpdateInputDTO inputDto) throws IOException {
        Response response = paymanUpdateManager.update(inputDto);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
    }

    public PaymanChangeStatusResponseBodyObject changePaymanStatus(PaymanChangeStatusInputDTO inputDTO) throws IOException {
        Response response = paymanChangeStatusManager.changeStatus(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }

    public PaymanReportResponseBodyObject getReport(String paymanId) throws IOException {
        Response response = paymanReportManager.getReport(paymanId);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }

    public PaymanTransactionsResponseBodyObject getTransactions(PaymanTransactionsInputDTO inputDTO) throws IOException {
        Response response = paymanTransactionsManager.getTransactions(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }

    public PaymanListResponseBodyObject getList(PaymanListInputDTO inputDTO) throws IOException {
        Response response = paymanListManager.getList(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }

    public List<PaymanMerchantPermissionResponseBodyObject> getMerchantPermissions() throws IOException {
        Response response = paymanMerchantPermissionsManager.getList();
//        List<Map<String, Object>> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }

    public PaymanTransactionsReportResponseBodyObject getTransactionsReport(PaymanTransactionsReportInputDTO inputDTO) throws IOException {
        Response response = paymanTransactionsReportManager.getReport(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }

    public PaymanTransactionsReportStatisticsResponseBodyObject getTransactionsReportStatistics(PaymanTransactionsReportStatisticsInputDTO inputDTO) throws IOException {
        Response response = paymanTransactionsReportStatisticsManager.getStatistics(inputDTO);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response);
        return null;
    }
}
