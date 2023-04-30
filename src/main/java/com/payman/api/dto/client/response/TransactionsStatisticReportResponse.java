package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.TransactionsStatisticReportResponseResult;
import com.payman.api.dto.provider.response.PaymanTransactionsStatisticReportResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TransactionsStatisticReportResponse {

    @JsonProperty("total_amount")
    private Double totalAmount;

    @JsonProperty("total_records")
    private Double totalRecords;

    private List<TransactionsStatisticReportResponseResult> results;

    public TransactionsStatisticReportResponse(PaymanTransactionsStatisticReportResponse paymanResponse) {
        this.totalAmount = paymanResponse.getTotalAmount();
        this.totalRecords = paymanResponse.getTotalRecords();
        this.results = Optional.ofNullable(paymanResponse.getResults()).orElse(Collections.emptyList())
                .stream().map(TransactionsStatisticReportResponseResult::new).collect(Collectors.toList());
    }
}
