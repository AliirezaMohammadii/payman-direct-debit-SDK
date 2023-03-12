package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.PayRequest;
import com.ighe3.api.dto.provider.response.PaymanPayResponse;
import com.ighe3.api.exception.BaseException;

public interface PaymanPayService {

    PaymanPayResponse pay(PayRequest inputDto) throws BaseException;
}
