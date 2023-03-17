package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.GetAllPaymansRequest;
import com.ighe3.api.dto.client.response.GetAllPaymansResponse;

import java.io.IOException;

public interface GetAllPaymansService {

    GetAllPaymansResponse get(GetAllPaymansRequest request) throws IOException;
}
