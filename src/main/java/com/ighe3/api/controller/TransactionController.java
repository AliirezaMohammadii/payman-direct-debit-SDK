package com.ighe3.api.controller;

import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.service.payman.TransactionsReportService;
import com.ighe3.api.service.payman.TransactionsReportStatisticsService;
import com.ighe3.api.service.payman.TransactionsService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactions(HttpServletRequest httpServletRequest,
                                  @RequestBody TransactionsRequest request) throws IOException {
        return transactionsService.getTransactions(httpServletRequest, request);
    }

    @PostMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactionsReport(HttpServletRequest httpServletRequest,
                                        @RequestBody TransactionsReportRequest request) throws IOException {
        return transactionsReportService.getReport(httpServletRequest, request);
    }

    @PostMapping("/report/statistics")
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactionsReportStatistics(HttpServletRequest httpServletRequest,
                                                  @RequestBody TransactionsReportStatisticsRequest request)
            throws IOException {
        return transactionsReportStatisticsService.getReportStatistics(httpServletRequest, request);
    }
}
