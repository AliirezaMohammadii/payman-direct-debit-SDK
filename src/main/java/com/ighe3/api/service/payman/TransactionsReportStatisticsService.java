package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportStatisticsResponse;
import com.ighe3.api.exception.BaseException;

public interface TransactionsReportStatisticsService {

    PaymanTransactionsReportStatisticsResponse getReportStatistics(TransactionsReportStatisticsRequest inputDto);
}
