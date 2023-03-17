package com.payman.api.dto.provider.response;

import com.payman.api.dto.TransactionsResponseResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanTransactionsResponse {

    private List<TransactionsResponseResult> results;
}
