package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.client.response.TransactionsReportStatisticsResponse;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportStatisticsResponse;
import com.ighe3.api.exception.BaseException;

import java.io.IOException;

public interface TransactionsReportStatisticsService {

    TransactionsReportStatisticsResponse getReportStatistics(TransactionsReportStatisticsRequest request) throws IOException;
}
