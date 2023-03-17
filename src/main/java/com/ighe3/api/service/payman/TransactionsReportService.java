package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import com.ighe3.api.dto.client.response.TransactionsReportResponse;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportResponse;
import com.ighe3.api.exception.BaseException;

import java.io.IOException;

public interface TransactionsReportService {

    TransactionsReportResponse getReport(TransactionsReportRequest request) throws IOException;
}
