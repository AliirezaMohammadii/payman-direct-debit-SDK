package com.payman.api.service.payman;

import com.payman.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.payman.api.dto.client.response.TransactionsReportStatisticsResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TransactionsReportStatisticsService {

    TransactionsReportStatisticsResponse getReportStatistics(HttpServletRequest httpServletRequest, TransactionsReportStatisticsRequest request) throws IOException;
}
