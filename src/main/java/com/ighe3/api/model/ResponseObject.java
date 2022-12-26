package com.ighe3.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseObject {
    private String body;
    private Integer statusCode;
}
