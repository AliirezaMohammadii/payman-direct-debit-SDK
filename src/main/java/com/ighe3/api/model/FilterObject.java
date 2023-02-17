package com.ighe3.api.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FilterObject {
    private List<String> paymanIds;
    private List<String> userIds;
    private String traceId;
    private String referenceId;
    // TODO: must be enum
    private String transactionType;
    private Double fromTransactionAmount;
    private Double toTransactionAmount;
    // TODO: must be dateTime
    private String fromTransactionDate;
    // TODO: must be dateTime
    private String toTransactionDate;
    private String note;
    private String sourceBankCode;
    private String destinationBankCode;
    // TODO: list of enums
    private List<String> payamnStatuses;
    // TODO: list of enums
    private List<String> transactionStatuses;
}
