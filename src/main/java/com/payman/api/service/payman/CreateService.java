package com.payman.api.service.payman;

import com.payman.api.dto.client.request.CreateRequest;
import com.payman.api.dto.client.response.CreateResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface CreateService {

    CreateResponse create(HttpServletRequest httpServletRequest, CreateRequest request) throws IOException;
}
