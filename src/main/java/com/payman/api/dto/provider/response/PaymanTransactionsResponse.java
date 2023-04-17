package com.payman.api.dto.provider.response;

import com.payman.api.dto.client.TransactionsResponseResult;
import com.payman.api.dto.provider.PaymanTransactionsResponseResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanTransactionsResponse {

    private List<PaymanTransactionsResponseResult> results;
}
