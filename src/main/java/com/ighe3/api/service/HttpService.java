package com.ighe3.api.service;


import com.ighe3.api.dto.Response;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface HttpService {

    <S> Response sendRequest(Request request, Class<S> serviceClass) throws IOException;

    Request createRequest(String url, Headers headers);

    Request createRequest(RequestBody body, String url, Headers headers);

    Headers createHeaders(HttpServletRequest httpServletRequest);

    Headers createHeaders(HttpServletRequest httpServletRequest, String accessToken);
}
