package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.GetAllPaymansRequestFilter;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PaymanGetAllPaymansRequestFilter {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("expiration_date_from")
    private String expirationDateFrom;

    @JsonProperty("expiration_date_to")
    private String expirationDateTo;

    @JsonProperty("max_daily_transactions_count_from")
    private Integer maxDailyTransactionsCountFrom;

    @JsonProperty("max_daily_transactions_count_to")
    private Integer maxDailyTransactionsCountTo;

    @JsonProperty("max_monthly_transactions_count_from")
    private Integer maxMonthlyTransactionsCountFrom;

    @JsonProperty("max_monthly_transactions_count_to")
    private Integer maxMonthlyTransactionsCountTo;

    @JsonProperty("start_date_from")
    private String startDateFrom;

    @JsonProperty("start_date_to")
    private String startDateTo;

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

    public PaymanGetAllPaymansRequestFilter(GetAllPaymansRequestFilter getAllPaymansRequestFilter) {
        if (getAllPaymansRequestFilter == null)
            return;

        this.bankCode = getAllPaymansRequestFilter.getBankCode();
        this.expirationDateFrom = DateUtils.epochMillisToPaymanDateTimeFormat(getAllPaymansRequestFilter.getExpirationDateFromEpochMillis());
        this.expirationDateTo = DateUtils.epochMillisToPaymanDateTimeFormat(getAllPaymansRequestFilter.getExpirationDateToEpochMillis());
        this.maxDailyTransactionsCountFrom = getAllPaymansRequestFilter.getMaxDailyTransactionsCountFrom();
        this.maxDailyTransactionsCountTo = getAllPaymansRequestFilter.getMaxDailyTransactionsCountTo();
        this.maxMonthlyTransactionsCountFrom = getAllPaymansRequestFilter.getMaxMonthlyTransactionsCountFrom();
        this.maxMonthlyTransactionsCountTo = getAllPaymansRequestFilter.getMaxMonthlyTransactionsCountTo();
        this.startDateFrom = DateUtils.epochMillisToPaymanDateTimeFormat(getAllPaymansRequestFilter.getStartDateFromEpochMillis());
        this.startDateTo = DateUtils.epochMillisToPaymanDateTimeFormat(getAllPaymansRequestFilter.getStartDateToEpochMillis());
        this.statuses = getAllPaymansRequestFilter.getStatuses();
        this.transactionMaxAmountFrom = getAllPaymansRequestFilter.getTransactionMaxAmountFrom();
        this.transactionMaxAmountTo = getAllPaymansRequestFilter.getTransactionMaxAmountTo();
        this.paymanId = getAllPaymansRequestFilter.getPaymanId();
        this.userIds = getAllPaymansRequestFilter.getUserIds();
        this.permissionIds = getAllPaymansRequestFilter.getPermissionIds();
    }
}
