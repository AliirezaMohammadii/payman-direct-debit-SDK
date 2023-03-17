package com.payman.api.service.payman;

import com.payman.api.dto.client.response.GetPaymanResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GetPaymanService {

    GetPaymanResponse get(HttpServletRequest httpServletRequest, String paymanId) throws IOException;
}
