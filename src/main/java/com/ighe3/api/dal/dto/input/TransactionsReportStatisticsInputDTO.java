package com.ighe3.api.dal.dto.input;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionsReportStatisticsInputDTO {
    private Date startDate;
    private Date endDate;
}
