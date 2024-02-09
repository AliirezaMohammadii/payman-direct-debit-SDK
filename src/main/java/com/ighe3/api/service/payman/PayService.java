package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.PayRequest;
import com.ighe3.api.dto.client.response.PayResponse;

import java.io.IOException;

public interface PayService {

    PayResponse pay(PayRequest request) throws IOException;
}
