package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PermissionIdDetail {

    private int id;

    private String name;

    @JsonProperty("display_name")
    private String displayName;
}
