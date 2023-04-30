package com.payman.api.dto.provider.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanTransactionsStatisticReportResponseResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanTransactionsStatisticReportResponse {

    @JsonProperty("total_amount")
    private Double totalAmount;

    @JsonProperty("total_records")
    private Double totalRecords;

    private List<PaymanTransactionsStatisticReportResponseResult> results;
}
