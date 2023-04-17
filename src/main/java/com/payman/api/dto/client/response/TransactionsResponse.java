package com.payman.api.dto.client.response;

import com.payman.api.dto.client.GetAllPaymansResponseResult;
import com.payman.api.dto.client.TransactionsResponseResult;
import com.payman.api.dto.provider.response.PaymanTransactionsResponse;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class TransactionsResponse {

    private List<TransactionsResponseResult> results;

    public TransactionsResponse(PaymanTransactionsResponse paymanResponse) {
        this.results = Optional.ofNullable(paymanResponse.getResults()).orElse(Collections.emptyList())
                .stream().map(TransactionsResponseResult::new).collect(Collectors.toList());
    }
}
