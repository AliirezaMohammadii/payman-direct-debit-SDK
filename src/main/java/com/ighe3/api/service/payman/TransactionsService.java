package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.dto.client.response.TransactionsResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TransactionsService {

    TransactionsResponse getTransactions(HttpServletRequest httpServletRequest, TransactionsRequest request) throws IOException;
}
