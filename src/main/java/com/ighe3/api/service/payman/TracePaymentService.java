package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TracePaymentRequest;
import com.ighe3.api.dto.client.response.TracePaymentResponse;

import java.io.IOException;

public interface TracePaymentService {

    TracePaymentResponse trace(TracePaymentRequest request) throws IOException;
}
