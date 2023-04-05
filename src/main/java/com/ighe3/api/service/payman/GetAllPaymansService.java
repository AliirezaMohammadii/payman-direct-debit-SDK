package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.GetAllPaymansRequest;
import com.ighe3.api.dto.client.response.GetAllPaymansResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GetAllPaymansService {

    GetAllPaymansResponse get(HttpServletRequest httpServletRequest, GetAllPaymansRequest request) throws IOException;
}
