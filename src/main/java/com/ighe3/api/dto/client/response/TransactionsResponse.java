package com.ighe3.api.dto.client.response;

import com.ighe3.api.dto.TransactionsResponseResult;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsResponse {

    private List<TransactionsResponseResult> results;
}
