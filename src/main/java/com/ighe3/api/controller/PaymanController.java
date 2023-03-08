package com.ighe3.api.controller;


import com.ighe3.api.dto.input.*;
import com.ighe3.api.dto.output.PaymanCreateOto;
import com.ighe3.api.service.payman.*;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    // TODO: no need to controller, must be removed.
    @GetMapping("/access-token")
    @ResponseStatus(HttpStatus.OK)
    public Object getAccessToken() throws Exception {
        return accessTokenService.getAccessToken();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public PaymanCreateOto createPayman(@RequestBody PaymanCreateIto inputDTO) throws Exception {
        return createService.create(inputDTO);
    }

    // TODO: no need to controller, must be removed.
    @GetMapping("/payman-id/{paymanCode}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPaymanId(@PathVariable String paymanCode) throws Exception {
        return paymanIdService.getPaymanId(paymanCode);
    }

    @GetMapping("/create/trace/{traceId}")
    @ResponseStatus(HttpStatus.OK)
    public Object traceCreatePayman(@PathVariable String traceId) throws Exception {
        return traceCreateService.trace(traceId);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public Object pay(@RequestBody PaymanPayIto inputDTO) throws Exception {
        return payService.pay(inputDTO);
    }

    @GetMapping("/pay/trace/{traceId}")
    @ResponseStatus(HttpStatus.OK)
    public Object tracePay(@PathVariable String traceId,
                           @RequestParam(required = false) String date) throws Exception {
        return tracePayService.trace(traceId, date);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Object updatePayman(@RequestBody PaymanUpdateIto inputDTO) throws Exception {
        return updateService.update(inputDTO);
    }

    @PostMapping("/change-status")
    @ResponseStatus(HttpStatus.OK)
    public Object changePaymanStatus(@RequestBody PaymanChangeStatusIto inputDTO) throws Exception {
        return changeStatusService.changeStatus(inputDTO);
    }

    @GetMapping("/report/{paymanId}")
    @ResponseStatus(HttpStatus.OK)
    public Object getReport(@PathVariable String paymanId) throws Exception {
        return reportService.getReport(paymanId);
    }

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactions(@RequestBody PaymanTransactionsIto inputDTO) throws Exception {
        return transactionsService.getTransactions(inputDTO);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Object getList(@RequestBody PaymanListIto inputDTO) throws Exception {
        return paymanListService.getList(inputDTO);
    }

    @GetMapping("/permissions")
    @ResponseStatus(HttpStatus.OK)
    public Object getMerchantPermissions() throws Exception {
        return merchantPermissionsService.getPermissionIds();
    }

    @PostMapping("/transactions/report")
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactionsReport(@RequestBody PaymanTransactionsReportIto inputDTO) throws Exception {
        return transactionsReportService.getReport(inputDTO);
    }

    @PostMapping("/transactions/report/statistics")
    @ResponseStatus(HttpStatus.OK)
    public Object getTransactionsReportStatistics(
            @RequestBody PaymanTransactionsReportStatisticsIto inputDTO) throws Exception {
        return transactionsReportStatisticsService.getReportStatistics(inputDTO);
    }
}
