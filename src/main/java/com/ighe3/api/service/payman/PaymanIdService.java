package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.GetPaymanIdRequest;
import com.ighe3.api.dto.client.response.GetPaymanIdResponse;
import com.ighe3.api.dto.provider.response.PaymanGetPaymanIdResponse;
import com.ighe3.api.exception.BaseException;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface PaymanIdService {

    GetPaymanIdResponse getPaymanId(HttpServletRequest httpServletRequest, String paymanCode) throws IOException;
}
