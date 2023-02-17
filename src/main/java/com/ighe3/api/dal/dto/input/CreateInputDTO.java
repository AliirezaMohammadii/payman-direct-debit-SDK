package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateInputDTO {
    private String bankCode;

    // TODO: remove
    private String userId;

    // TODO: remove
    private List<Integer> permissionIds;

    private Date startDate;
    private Date expirationDate;
    private Integer maxDailyTransactionCount;
    private Integer maxMonthlyTransactionCount;
    private Double maxTransactionAmount;
    private Double dailyMaxTransactionAmount;

    // TODO: remove
    private String redirectUrl;

    // TODO: remove
    private String traceId;

    // TODO: remove
    private String mobileNumber;

    // TODO: remove
    private String nationalCode;
}
