package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanContract;
import com.payman.api.dto.provider.PaymanGetAllPaymansRequestFilter;
import com.payman.api.utils.DateUtils;
import lombok.Data;

import java.util.List;

@Data
public class GetAllPaymansRequestFilter {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("expiration_date_from")
    private Long expirationDateFromEpochMillis;

    @JsonProperty("expiration_date_to")
    private Long expirationDateToEpochMillis;

    @JsonProperty("max_daily_transactions_count_from")
    private Integer maxDailyTransactionsCountFrom;

    @JsonProperty("max_daily_transactions_count_to")
    private Integer maxDailyTransactionsCountTo;

    @JsonProperty("max_monthly_transactions_count_from")
    private Integer maxMonthlyTransactionsCountFrom;

    @JsonProperty("max_monthly_transactions_count_to")
    private Integer maxMonthlyTransactionsCountTo;

    @JsonProperty("start_date_from")
    private Long startDateFromEpochMillis;

    @JsonProperty("start_date_to")
    private Long startDateToEpochMillis;

    private List<String> statuses;

    @JsonProperty("transaction_max_amount_from")
    private Double transactionMaxAmountFrom;

    @JsonProperty("transaction_max_amount_to")
    private Double transactionMaxAmountTo;

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("user_ids")
    private List<String> userIds;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    public GetAllPaymansRequestFilter(PaymanGetAllPaymansRequestFilter paymanGetAllPaymansRequestFilter) {
        this.bankCode = paymanGetAllPaymansRequestFilter.getBankCode();
        this.expirationDateFromEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanGetAllPaymansRequestFilter.getExpirationDateFrom());
        this.expirationDateToEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanGetAllPaymansRequestFilter.getExpirationDateTo());
        this.maxDailyTransactionsCountFrom = paymanGetAllPaymansRequestFilter.getMaxDailyTransactionsCountFrom();
        this.maxDailyTransactionsCountTo = paymanGetAllPaymansRequestFilter.getMaxDailyTransactionsCountTo();
        this.maxMonthlyTransactionsCountFrom = paymanGetAllPaymansRequestFilter.getMaxMonthlyTransactionsCountFrom();
        this.maxMonthlyTransactionsCountTo = paymanGetAllPaymansRequestFilter.getMaxMonthlyTransactionsCountTo();
        this.startDateFromEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanGetAllPaymansRequestFilter.getStartDateFrom());
        this.startDateToEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanGetAllPaymansRequestFilter.getStartDateTo());
        this.statuses = paymanGetAllPaymansRequestFilter.getStatuses();
        this.transactionMaxAmountFrom = paymanGetAllPaymansRequestFilter.getTransactionMaxAmountFrom();
        this.transactionMaxAmountTo = paymanGetAllPaymansRequestFilter.getTransactionMaxAmountTo();
        this.paymanId = paymanGetAllPaymansRequestFilter.getPaymanId();
        this.userIds = paymanGetAllPaymansRequestFilter.getUserIds();
        this.permissionIds = paymanGetAllPaymansRequestFilter.getPermissionIds();
    }
}
