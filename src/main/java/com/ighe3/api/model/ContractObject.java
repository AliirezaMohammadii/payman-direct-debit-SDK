package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ContractObject {
    private Date startDate;
    private Date expirationDate;
    private Integer maxDailyTransactionCount;
    private Integer maxMonthlyTransactionCount;
    private Double maxTransactionAmount;
    private Double dailyMaxTransactionAmount;
}
