package com.payman.api.service.payman;

import com.payman.api.dto.client.request.TransactionsRequest;
import com.payman.api.dto.client.response.TransactionsResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TransactionsService {

    TransactionsResponse getTransactions(HttpServletRequest httpServletRequest, TransactionsRequest request) throws IOException;
}
