package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.GetPaymanIdRequest;
import com.ighe3.api.dto.client.response.GetPaymanIdResponse;
import com.ighe3.api.dto.provider.response.PaymanGetPaymanIdResponse;
import com.ighe3.api.exception.BaseException;

import java.io.IOException;

public interface PaymanIdService {

    GetPaymanIdResponse getPaymanId(GetPaymanIdRequest request) throws IOException;
}
