package com.ighe3.api.service.payman;

import com.ighe3.api.dto.provider.response.PaymanReportResponse;
import com.ighe3.api.exception.BaseException;

public interface ReportService {

    PaymanReportResponse getReport(String paymanId) throws BaseException;
}
