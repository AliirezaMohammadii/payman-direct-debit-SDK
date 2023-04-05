package com.payman.api.dto.client.response;

import com.payman.api.dto.TransactionsResponseResult;
import com.payman.api.dto.provider.response.PaymanTransactionsResponse;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsResponse {

    private List<TransactionsResponseResult> results;

    public TransactionsResponse(PaymanTransactionsResponse paymanResponse) {
        this.results = paymanResponse.getResults();
    }
}
