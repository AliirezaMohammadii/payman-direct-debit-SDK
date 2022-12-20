package com.ighe3.api.management;

import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.Urls;
import okhttp3.*;

import java.io.IOException;

public abstract class PaymanBaseManager {

    protected Response sendRequest(Request request) {
        OkHttpClient client = GeneralUtils.buildOkhttpClient();
        Response response = executeSending(client, request);
        printResponse(response);
        return response;
    }

    protected Request getRequest(String url, Headers headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .build();
        return request;
    }

    protected Request getRequest(RequestBody body, String url, Headers headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build();
        return request;
    }

    protected void printResponse(Response response) {
        System.out.println("status code: " + response.code());
        try {
            System.out.println(GeneralUtils.getBeautifiedJson(response.body().string()));
        }
        catch (IOException e) {
            System.err.println("error occurred while converting request body to string.");
        }
    }

    protected Response executeSending(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Headers getHeaders() throws IOException;
}
