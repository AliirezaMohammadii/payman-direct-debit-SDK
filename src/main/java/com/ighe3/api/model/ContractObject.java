package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContractObject {
    private String startDate;
    private String expirationDate;
    private Integer maxDailyTransactionCount;
    private Integer maxMonthlyTransactionCount;
    private Double maxTransactionAmount;
    private Double dailyMaxTransactionAmount;
}
