package org.mt.androidlibrary.net.interceptor;

import android.util.ArrayMap;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:RecyclerView的分割线
 * @Author:zwna
 * @Date:2019-04-29
 */
public class HeaderInterceptor implements Interceptor {

    private ArrayMap<String,String> commonHeaders;

    public HeaderInterceptor(ArrayMap<String,String> commonHeaders) {
        this.commonHeaders = commonHeaders;
    }

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .method(original.method(), original.body());
        //动态添加heads
        if (null != commonHeaders) {
            for (Map.Entry<String, String> entry : commonHeaders.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return chain.proceed(requestBuilder.build());
    }
}
