package org.mt.androidlibrary.net.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.mt.androidlibrary.net.config.ApiConfig;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:RecyclerView的分割线
 * @Author:zwna
 * @Date:2019-04-29
 */
public class HeaderInterceptor implements Interceptor {

    private ApiConfig apiConfig;

    public HeaderInterceptor(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder reqBuilder = request.newBuilder();
//        reqBuilder.header("Content-type","application/json")
//                .header("Authorization","author")
//                .addHeader("Accept-Encoding", "identity");

        //动态添加heads
        Map<String, String> heads = apiConfig.getHeads();
        if (null != heads) {
            for (Map.Entry<String, String> entry : heads.entrySet()) {
                reqBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return chain.proceed(reqBuilder.build());
    }
}
