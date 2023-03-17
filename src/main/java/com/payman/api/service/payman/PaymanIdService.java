package com.payman.api.service.payman;

import com.payman.api.dto.client.response.GetPaymanIdResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface PaymanIdService {

    GetPaymanIdResponse getPaymanId(HttpServletRequest httpServletRequest, String paymanCode) throws IOException;
}
