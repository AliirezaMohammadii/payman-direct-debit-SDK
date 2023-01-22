package com.ighe3.api.model.requestBody;

import com.ighe3.api.model.FilterObject;
import lombok.Data;

@Data
public class PaymanTransactionsRequestBodyObject {

    private FilterObject filter;
    private String length;
    private String offset;
}
