package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanMerchantPermission {
    private Integer id;
    private String name;
    @JsonProperty("display_name")
    private String displayName;
}
