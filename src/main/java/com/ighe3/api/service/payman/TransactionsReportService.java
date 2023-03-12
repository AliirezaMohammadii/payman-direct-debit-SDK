package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportResponse;
import com.ighe3.api.exception.BaseException;

public interface TransactionsReportService {

    PaymanTransactionsReportResponse getReport(TransactionsReportRequest inputDto) throws BaseException;
}
