package com.ighe3.api.controller;


import com.ighe3.api.dal.dto.payman.input.*;
import com.ighe3.api.service.PaymanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payman")
public class PaymanController implements BaseController {

    private final PaymanService paymanService;

    public PaymanController(PaymanService paymanService) {
        this.paymanService = paymanService;
    }

    @GetMapping("/access-token")
    @ResponseStatus(HttpStatus.OK)
    public String getAccessToken() throws Exception {
        return paymanService.getAccessToken();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPayman(@RequestBody PaymanCreateInputDTO inputDTO) throws Exception {
        paymanService.createPayman(inputDTO);
    }

    @GetMapping("/payman-id/{paymanCode}")
    @ResponseStatus(HttpStatus.OK)
    public String getPaymanId(@PathVariable String paymanCode) throws Exception {
        return paymanService.getPaymanId(paymanCode);
    }

    @GetMapping("/create/trace/{trace_id}")
    @ResponseStatus(HttpStatus.OK)
    public Object traceCreatePayman(@PathVariable String trace_id) throws Exception {
        return paymanService.traceCreatePayman(trace_id);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public void pay(@RequestBody PaymanPayInputDTO inputDTO) throws Exception {
        paymanService.pay(inputDTO);
    }

    @GetMapping("/pay/trace/{trace_id}")
    @ResponseStatus(HttpStatus.OK)
    public Object tracePay(@PathVariable String trace_id,
                           @RequestParam(required = false) String date) throws Exception {
        return paymanService.tracePay(trace_id, date);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updatePayman(@RequestBody PaymanUpdateInputDTO inputDTO) throws Exception {
        paymanService.updatePayman(inputDTO);
    }

    @PostMapping("/change_status")
    @ResponseStatus(HttpStatus.OK)
    public void changePaymanStatus(@RequestBody PaymanChangeStatusInputDTO inputDTO) throws Exception {
        paymanService.changePaymanStatus(inputDTO);
    }

    @GetMapping("/report/{payman_id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getReport(@PathVariable String payman_id) throws Exception {
        return paymanService.getReport(payman_id);
    }

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public void getTransactions(@RequestBody PaymanTransactionsInputDTO inputDTO) throws Exception {
        paymanService.getTransactions(inputDTO);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public void getList(@RequestBody PaymanListInputDTO inputDTO) throws Exception {
        paymanService.getList(inputDTO);
    }

    @GetMapping("/merchant_permissions")
    @ResponseStatus(HttpStatus.OK)
    public Object getMerchantPermissions() throws Exception {
        return paymanService.getMerchantPermissions();
    }

    @PostMapping("/transactions/report")
    @ResponseStatus(HttpStatus.OK)
    public void getTransactionsReport(@RequestBody PaymanTransactionsReportInputDTO inputDTO) throws Exception {
        paymanService.getTransactionsReport(inputDTO);
    }

    @PostMapping("/transactions/report/statistics")
    @ResponseStatus(HttpStatus.OK)
    public void getTransactionsReportStatistics(
            @RequestBody PaymanTransactionsReportStatisticsInputDTO inputDTO) throws Exception {
        paymanService.getTransactionsReportStatistics(inputDTO);
    }
}
