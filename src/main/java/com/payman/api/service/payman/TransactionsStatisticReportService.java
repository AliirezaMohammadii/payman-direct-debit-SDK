package com.payman.api.service.payman;

import com.payman.api.dto.client.request.TransactionsStatisticReportRequest;
import com.payman.api.dto.client.response.TransactionsStatisticReportResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TransactionsStatisticReportService {

    TransactionsStatisticReportResponse getReport(HttpServletRequest httpServletRequest, TransactionsStatisticReportRequest request) throws IOException;
}
