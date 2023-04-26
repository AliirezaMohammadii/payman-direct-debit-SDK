package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.request.TransactionsReportRequest;
import com.payman.api.utils.DateUtils;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymanTransactionsReportRequest {

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("length")
    private Integer length;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("bank_code")
    private String bankCode;

    public PaymanTransactionsReportRequest(TransactionsReportRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.startDate = DateUtils.epochMillisToPaymanDateTimeFormat(request.getStartDateEpochMillis());
        this.endDate = DateUtils.epochMillisToPaymanDateTimeFormat(request.getEndDateEpochMillis());
        this.bankCode = request.getBankCode();
    }
}
