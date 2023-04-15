package com.payman.api.controller;

import com.payman.api.dto.client.request.TransactionsReportRequest;
import com.payman.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.payman.api.dto.client.request.TransactionsRequest;
import com.payman.api.service.payman.TransactionsReportService;
import com.payman.api.service.payman.TransactionsReportStatisticsService;
import com.payman.api.service.payman.TransactionsService;
import com.payman.api.dto.client.response.TransactionsReportResponse;
import com.payman.api.dto.client.response.TransactionsReportStatisticsResponse;
import com.payman.api.dto.client.response.TransactionsResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

    private final TransactionsService transactionsService;
    private final TransactionsReportService transactionsReportService;
    private final TransactionsReportStatisticsService transactionsReportStatisticsService;

    public TransactionController(TransactionsService transactionsService,
                                 TransactionsReportService transactionsReportService,
                                 TransactionsReportStatisticsService transactionsReportStatisticsService) {

        this.transactionsService = transactionsService;
        this.transactionsReportService = transactionsReportService;
        this.transactionsReportStatisticsService = transactionsReportStatisticsService;
    }

    @PostMapping
    public TransactionsResponse getTransactions(HttpServletRequest httpServletRequest,
                                                @RequestBody TransactionsRequest request) throws IOException {
        return transactionsService.getTransactions(httpServletRequest, request);
    }

    @PostMapping("/report")
    public TransactionsReportResponse getTransactionsReport(HttpServletRequest httpServletRequest,
                                                            @RequestBody TransactionsReportRequest request) throws IOException {
        return transactionsReportService.getReport(httpServletRequest, request);
    }

    @PostMapping("/report/statistics")
    public TransactionsReportStatisticsResponse getTransactionsReportStatistics(HttpServletRequest httpServletRequest,
                                                                                @RequestBody TransactionsReportStatisticsRequest request)
            throws IOException {
        return transactionsReportStatisticsService.getReportStatistics(httpServletRequest, request);
    }
}
