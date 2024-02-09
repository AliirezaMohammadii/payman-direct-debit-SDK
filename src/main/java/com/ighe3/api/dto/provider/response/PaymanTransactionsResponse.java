package com.ighe3.api.dto.provider.response;

import com.ighe3.api.dto.TransactionsResponseResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanTransactionsResponse {

    private List<TransactionsResponseResult> results;
}
