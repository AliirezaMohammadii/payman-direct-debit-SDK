package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionsReportInputDTO {
    private Integer offset;
    private Integer length;
    private String startDate;
    private String endDate;
    private String bankCode;
}
