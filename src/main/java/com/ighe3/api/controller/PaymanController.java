package com.ighe3.api.controller;


import com.ighe3.api.dto.input.*;
import com.ighe3.api.service.payman.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class PaymanController implements IController {

    private final AccessTokenService accessTokenService;
    private final CreateService createService;
    private final PaymanIdService paymanIdService;
    private final TraceCreateService traceCreateService;
    private final PayService payService;
    private final TracePayService tracePayService;
    private final UpdateService updateService;
    private final ChangeStatusService changeStatusService;
    private final ReportService reportService;
    private final TransactionsService transactionsService;
    private final PaymanListService paymanListService;
    private final MerchantPermissionsService merchantPermissionsService;
    private final TransactionsReportService transactionsReportService;
    private final TransactionsReportStatisticsService transactionsReportStatisticsService;

    public PaymanController(AccessTokenService accessTokenService,
                            CreateService createService,
                            PaymanIdService paymanIdService,
                            TraceCreateService traceCreateService,
                            PayService payService,
                            TracePayService tracePayService,
                            UpdateService updateService,
                            ChangeStatusService changeStatusService,
                            ReportService reportService,
                            TransactionsService transactionsService,
                            PaymanListService paymanListService,
                            MerchantPermissionsService merchantPermissionsService,
                            TransactionsReportService transactionsReportService,
                            TransactionsReportStatisticsService transactionsReportStatisticsService) {
        this.accessTokenService = accessTokenService;
        this.createService = createService;
        this.paymanIdService = paymanIdService;
        this.traceCreateService = traceCreateService;
        this.payService = payService;
        this.tracePayService = tracePayService;
        this.updateService = updateService;
        this.changeStatusService = changeStatusService;
        this.reportService = reportService;
        this.transactionsService = transactionsService;
        this.paymanListService = paymanListService;
        this.merchantPermissionsService = merchantPermissionsService;
        this.transactionsReportService = transactionsReportService;
        this.transactionsReportStatisticsService = transactionsReportStatisticsService;
    }

    @GetMapping("/access-token")
    @ResponseStatus(HttpStatus.OK)
    public Object getAccessToken() throws Exception {
        return accessTokenService.getAccessToken();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPayman(@RequestBody PaymanCreateIto inputDTO) throws Exception {
        createService.create(inputDTO);
    }

    @GetMapping("/payman-id/{paymanCode}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPaymanId(@PathVariable String paymanCode) throws Exception {
        return paymanIdService.getPaymanId(paymanCode);
    }

    @GetMapping("/create/trace/{trace_id}")
    @ResponseStatus(HttpStatus.OK)
    public Object traceCreatePayman(@PathVariable String trace_id) throws Exception {
        return traceCreateService.trace(trace_id);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public void pay(@RequestBody PaymanPayIto inputDTO) throws Exception {
        payService.pay(inputDTO);
    }

    @GetMapping("/pay/trace/{trace_id}")
    @ResponseStatus(HttpStatus.OK)
    public Object tracePay(@PathVariable String trace_id,
                           @RequestParam(required = false) String date) throws Exception {
        return tracePayService.trace(trace_id, date);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updatePayman(@RequestBody PaymanUpdateIto inputDTO) throws Exception {
        updateService.update(inputDTO);
    }

    @PostMapping("/change-status")
    @ResponseStatus(HttpStatus.OK)
    public void changePaymanStatus(@RequestBody PaymanChangeStatusIto inputDTO) throws Exception {
        changeStatusService.changeStatus(inputDTO);
    }

    @GetMapping("/report/{payman_id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getReport(@PathVariable String payman_id) throws Exception {
        return reportService.getReport(payman_id);
    }

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public void getTransactions(@RequestBody PaymanTransactionsIto inputDTO) throws Exception {
        transactionsService.getTransactions(inputDTO);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public void getList(@RequestBody PaymanListIto inputDTO) throws Exception {
        paymanListService.getList(inputDTO);
    }

    @GetMapping("/permissions")
    @ResponseStatus(HttpStatus.OK)
    public Object getMerchantPermissions() throws Exception {
        return merchantPermissionsService.getPermissions();
    }

    @PostMapping("/transactions/report")
    @ResponseStatus(HttpStatus.OK)
    public void getTransactionsReport(@RequestBody PaymanTransactionsReportIto inputDTO) throws Exception {
        transactionsReportService.getReport(inputDTO);
    }

    @PostMapping("/transactions/report/statistics")
    @ResponseStatus(HttpStatus.OK)
    public void getTransactionsReportStatistics(
            @RequestBody PaymanTransactionsReportStatisticsIto inputDTO) throws Exception {
        transactionsReportStatisticsService.getReportStatistics(inputDTO);
    }
}
