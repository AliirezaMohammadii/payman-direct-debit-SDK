package com.payman.api.service.payman;

import com.payman.api.dto.client.request.PayRequest;
import com.payman.api.dto.client.response.PayResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface PayService {

    PayResponse pay(HttpServletRequest httpServletRequest, PayRequest request) throws IOException;
}
