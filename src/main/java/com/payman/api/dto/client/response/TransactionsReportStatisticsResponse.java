package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.GetAllPaymansResponseResult;
import com.payman.api.dto.client.TransactionsReportStatisticsResponseResult;
import com.payman.api.dto.provider.response.PaymanTransactionsReportStatisticsResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TransactionsReportStatisticsResponse {

    @JsonProperty("total_amount")
    private Double totalAmount;

    @JsonProperty("total_records")
    private Double totalRecords;

    private List<TransactionsReportStatisticsResponseResult> results;

    public TransactionsReportStatisticsResponse(PaymanTransactionsReportStatisticsResponse paymanResponse) {
        this.totalAmount = paymanResponse.getTotalAmount();
        this.totalRecords = paymanResponse.getTotalRecords();
        this.results = Optional.ofNullable(paymanResponse.getResults()).orElse(Collections.emptyList())
                .stream().map(TransactionsReportStatisticsResponseResult::new).collect(Collectors.toList());
    }
}
