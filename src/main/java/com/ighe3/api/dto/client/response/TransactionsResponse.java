package com.ighe3.api.dto.client.response;

import com.ighe3.api.dto.TransactionsResponseResult;
import com.ighe3.api.dto.provider.response.PaymanTransactionsResponse;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsResponse {

    private List<TransactionsResponseResult> results;

    public TransactionsResponse(PaymanTransactionsResponse paymanResponse) {
        this.results = paymanResponse.getResults();
    }
}
