package com.payman.api.dto.client.response;

import com.payman.api.dto.client.TransactionsReportResponseResult;
import com.payman.api.dto.provider.PaymanTransactionsReportResponseResult;
import com.payman.api.mapper.JsonMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TransactionsReportResponse {

    private List<TransactionsReportResponseResult> results;

    public TransactionsReportResponse(List<HashMap<String, Object>> paymanResponse) {
        this.results = Optional.ofNullable(paymanResponse).orElse(Collections.emptyList())
                .stream().map(this::mapHashMapItemToResponseResult).map(TransactionsReportResponseResult::new).collect(Collectors.toList());
    }

    private PaymanTransactionsReportResponseResult mapHashMapItemToResponseResult(HashMap<String, Object> item) {
        String json = JsonMapper.mapJavaObjectToJson(item);
        return (PaymanTransactionsReportResponseResult) JsonMapper.mapJsonToJavaObject(json, PaymanTransactionsReportResponseResult.class);
    }
}
