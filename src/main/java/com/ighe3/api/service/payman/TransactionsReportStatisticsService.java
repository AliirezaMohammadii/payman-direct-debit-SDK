package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.client.response.TransactionsReportStatisticsResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TransactionsReportStatisticsService {

    TransactionsReportStatisticsResponse getReportStatistics(HttpServletRequest httpServletRequest, TransactionsReportStatisticsRequest request) throws IOException;
}
