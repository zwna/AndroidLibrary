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
        //获取原来的request
        Request oldRequest = chain.request();
        //获取url
        String url = oldRequest.url().toString();
        //判断请求 get还是post
        if (oldRequest.method().equalsIgnoreCase("GET")) {
            //非空判断
            if (params != null && params.size() > 0) {
                StringBuilder builder = new StringBuilder(url);

                //拼接公共请求参数
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    builder.append("&" + entry.getKey() + "=" + entry.getValue());
                }

                String surl = builder.toString();
                //判断地址有没有？没有则添加
                if (!surl.contains("?")) {
                    surl = surl.replaceFirst("&", "?");
                }
                //根据老的生成新的
                Request request = oldRequest.newBuilder()
                        .url(surl)
                        .build();
                return chain.proceed(request);
            }
        } else {
            if (params != null && params.size() > 0) {
                RequestBody body = oldRequest.body();
                if (body != null && body instanceof FormBody) {
                    FormBody formBody = (FormBody) body;
                    //把原来的body添加到新body里
                    FormBody.Builder sbuilder = new FormBody.Builder();
                    //为了防止添加重复的key 和 value
                    HashMap<String, String> hashMap = new HashMap<>();
                    for (int i=0;i<formBody.size();i++){
                        sbuilder.add(formBody.encodedName(i),formBody.encodedValue(i));
                        hashMap.put(formBody.encodedName(i),formBody.encodedValue(i));
                    }
                    //把公共参数添加到新的body中
                    for (Map.Entry<String,Object> entry: params.entrySet()){
                        if (!hashMap.containsKey(entry.getKey())){
                            sbuilder.add(entry.getKey(),(String) entry.getValue());
                        }
                    }
                    FormBody sformBody = sbuilder.build();
                    //根据老的生成新的
                    Request request = oldRequest.newBuilder()
                            .post(sformBody)
                            .build();
                    return chain.proceed(request);
                }
            }
        }

        return chain.proceed(oldRequest);
    }

}