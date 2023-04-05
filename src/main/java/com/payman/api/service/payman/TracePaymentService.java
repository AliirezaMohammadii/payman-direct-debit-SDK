package com.payman.api.service.payman;

import com.payman.api.dto.client.response.TracePaymentResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TracePaymentService {

    TracePaymentResponse trace(HttpServletRequest httpServletRequest, String traceId, String date) throws IOException;
}
