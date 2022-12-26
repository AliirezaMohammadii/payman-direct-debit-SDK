package com.ighe3.api.model.payman.requestBody;

import com.ighe3.api.model.payman.PaymanRequestFilterObject;
import lombok.Data;

@Data
public class PaymanListRequestBodyObject {

    private PaymanRequestFilterObject filter;
    private String length;
    private String offset;
}
