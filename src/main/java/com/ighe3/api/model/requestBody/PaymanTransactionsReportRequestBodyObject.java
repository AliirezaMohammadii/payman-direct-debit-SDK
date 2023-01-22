package com.ighe3.api.model.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanTransactionsReportRequestBodyObject {

    private Integer offset;

    private Integer length;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("bank_code")
    private String bankCode;
}
