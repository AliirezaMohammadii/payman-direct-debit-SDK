package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.dto.client.response.TransactionsResponse;
import com.ighe3.api.dto.provider.response.PaymanTransactionsResponse;
import com.ighe3.api.exception.BaseException;

import java.io.IOException;

public interface TransactionsService {

    TransactionsResponse getTransactions(TransactionsRequest request) throws IOException;
}
