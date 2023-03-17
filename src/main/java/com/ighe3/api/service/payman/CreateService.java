package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.CreateRequest;
import com.ighe3.api.dto.client.response.CreateResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface CreateService {

    CreateResponse create(HttpServletRequest httpServletRequest, CreateRequest request) throws IOException;
}
