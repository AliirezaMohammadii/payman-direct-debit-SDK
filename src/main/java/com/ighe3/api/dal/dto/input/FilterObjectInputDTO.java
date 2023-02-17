package com.ighe3.api.dal.dto.input;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FilterObjectInputDTO {
    private List<String> paymanIds;
    private List<String> userIds;
    private String traceId;
    private String referenceId;

    private String transactionType;
    private Double fromTransactionAmount;
    private Double toTransactionAmount;

    private String fromTransactionDate;

    private String toTransactionDate;
    private String note;
    private String sourceBankCode;
    private String destinationBankCode;

    private List<String> payamnStatuses;

    private List<String> transactionStatuses;
}
