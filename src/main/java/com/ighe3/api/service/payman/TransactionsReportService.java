package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import com.ighe3.api.dto.client.response.TransactionsReportResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TransactionsReportService {

    TransactionsReportResponse getReport(HttpServletRequest httpServletRequest, TransactionsReportRequest request) throws IOException;
}
