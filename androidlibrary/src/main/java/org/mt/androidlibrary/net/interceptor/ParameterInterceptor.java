package org.mt.androidlibrary.net.interceptor;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口gong参数，支持 Get、POST 方式
 * @Description: 参数拦截器，为接口统一添加共通参数，如版本号、Token 等
 * @Author: sh
 * @Date: 2019/2/20
 */
public  class ParameterInterceptor implements Interceptor {

    private HashMap<String,Object> params;

    public ParameterInterceptor(HashMap<String, Object> params) {
        this.params = params;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (params != null && params.size() != 0) {
            if (request.method().equals("GET")) {// 为GET方式统一添加请求参数
                HttpUrl.Builder modifiedUrl = request.url().newBuilder()
                        .scheme(request.url().scheme())
                        .host(request.url().host());

                if (params.size() != 0) {
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        modifiedUrl.addQueryParameter(entry.getKey(), entry.getValue().toString());
                    }
                }

                request = request.newBuilder()
                        .method(request.method(), request.body())
                        .url(modifiedUrl.build())
                        .build();

            } else if (request.method().equals("POST")) {// 为POST方式统一添加请求参数
                if (request.body() instanceof FormBody) {
                    FormBody.Builder body = new FormBody.Builder();
                    if (params.size() != 0) {
                        for (Map.Entry<String, Object> entry : params.entrySet()) {
                            body.addEncoded(entry.getKey(), entry.getValue().toString());
                        }
                    }
                    body.build();

                    FormBody oldBody = (FormBody) request.body();
                    if (oldBody != null && oldBody.size() != 0) {
                        for (int i = 0; i < oldBody.size(); i++) {
                            body.addEncoded(oldBody.encodedName(i), oldBody.encodedValue(i));
                        }
                    }

                    request = request.newBuilder()
                            .post(body.build())
                            .build();
                }
            }
        }
        return chain.proceed(request);
    }

}