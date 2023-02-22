package com.ighe3.api.model.request;

import com.ighe3.api.model.FilterObject;
import lombok.Data;

@Data
public class PaymanListRequestBodyObject {

    private FilterObject filter;
    private String length;
    private String offset;
}
