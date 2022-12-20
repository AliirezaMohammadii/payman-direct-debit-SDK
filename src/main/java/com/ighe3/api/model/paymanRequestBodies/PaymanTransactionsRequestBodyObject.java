package com.ighe3.api.model.paymanRequestBodies;

import com.ighe3.api.model.PaymanRequestFilterObject;
import lombok.Data;

@Data
public class PaymanTransactionsRequestBodyObject {

    private PaymanRequestFilterObject filter;
    private String length;
    private String offset;
}
