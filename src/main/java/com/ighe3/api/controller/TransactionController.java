package com.ighe3.api.controller;

import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.service.payman.TransactionsReportService;
import com.ighe3.api.service.payman.TransactionsReportStatisticsService;
import com.ighe3.api.service.payman.TransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Object getTransactions(@RequestBody TransactionsRequest inputDTO) throws RuntimeException {
        return transactionsService.getTransactions(inputDTO);
    }

    @PostMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactionsReport(@RequestBody TransactionsReportRequest inputDTO) throws RuntimeException {
        return transactionsReportService.getReport(inputDTO);
    }

    @PostMapping("/report/statistics")
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactionsReportStatistics(
            @RequestBody TransactionsReportStatisticsRequest inputDTO) throws RuntimeException {
        return transactionsReportStatisticsService.getReportStatistics(inputDTO);
    }
}
