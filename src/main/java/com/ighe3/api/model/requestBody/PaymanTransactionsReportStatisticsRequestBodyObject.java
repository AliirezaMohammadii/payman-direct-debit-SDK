package com.ighe3.api.model.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanTransactionsReportStatisticsRequestBodyObject {

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;
}
