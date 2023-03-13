package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsResponse;
import com.ighe3.api.exception.BaseException;

public interface TransactionsService {

    PaymanTransactionsResponse getTransactions(TransactionsRequest inputDto);
}
