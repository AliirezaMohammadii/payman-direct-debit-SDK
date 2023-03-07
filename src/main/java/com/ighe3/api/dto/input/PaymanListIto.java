package com.ighe3.api.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.PaymanRequestFilter;
import lombok.Data;

@Data
public class PaymanListIto {
    private Integer offset;
    private Integer length;
    private PaymanRequestFilter filter;
}
