package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdateInputDTO {
    private String bankCode;
    private String paymanId;
    private Date expirationDate;
    private Integer maxDailyTransactionCount;
    private Integer maxMonthlyTransactionCount;
    private Double maxTransactionAmount;
    private Double dailyMaxTransactionAmount;

    // TODO: remove
    private String redirectUrl;
}
