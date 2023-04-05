package com.payman.api.service.payman;

import com.payman.api.dto.client.request.GetAllPaymansRequest;
import com.payman.api.dto.client.response.GetAllPaymansResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GetAllPaymansService {

    GetAllPaymansResponse get(HttpServletRequest httpServletRequest, GetAllPaymansRequest request) throws IOException;
}
