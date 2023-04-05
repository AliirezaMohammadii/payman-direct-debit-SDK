package com.payman.api.service.payman;

import com.payman.api.dto.client.request.TransactionsReportRequest;
import com.payman.api.dto.client.response.TransactionsReportResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TransactionsReportService {

    TransactionsReportResponse getReport(HttpServletRequest httpServletRequest, TransactionsReportRequest request) throws IOException;
}
