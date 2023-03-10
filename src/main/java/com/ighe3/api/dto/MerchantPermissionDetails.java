package com.ighe3.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MerchantPermissionDetails {

    private int id;

    private String name;

    @JsonProperty("display_name")
    private String displayName;
}
