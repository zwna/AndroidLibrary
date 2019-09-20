package org.mt.androidlibrary.net.interceptor;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @Description:网络请求的拦截器，可以查看到网络请求的字段以及响应数据
 * @Author:zwna
 * @Date:2019-04-29
 */
public class JsonInterceptor implements Interceptor {

    private Charset UTF8 = Charset.forName("UTF-8");


    public Response intercept(@NotNull Chain chain) throws IOException{

        Request request = chain.request();
        RequestBody requestBody = request.body();

        String body = null;

        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            body = buffer.readString(charset);
        }

        LogUtils.d("OkHttp", "请求头:\n" + request.headers() + "\nurl：" + request.url() + "\nbody:" + body);

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();
        String rBody = null;

        if (requestBody != null) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }

            }
            rBody = buffer.clone().readString(charset);
        }

        LogUtils.d("OkHttp", "响应：" + new Gson().toJson(rBody));

        return response;
    }
}