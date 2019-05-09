package org.mt.androidlibrary.net.interceptor;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:添加公共参数的拦截器
 * @Author:zwna
 * @Date:2019-05-08
 */
public class CommonParamInterceptor implements Interceptor {

    private HashMap<String,Object> commonParam;

    public CommonParamInterceptor(HashMap<String,Object> hashMap) {
        commonParam = hashMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request;
        HttpUrl.Builder modifiedUrlBuilder = originalRequest.url().newBuilder();

        for(Map.Entry<String,Object> param:commonParam.entrySet()){
            modifiedUrlBuilder.addQueryParameter(param.getKey(),(String) param.getValue());
        }

        request = originalRequest.newBuilder().url(modifiedUrlBuilder.build()).build();
        return chain.proceed(request);
    }
}
