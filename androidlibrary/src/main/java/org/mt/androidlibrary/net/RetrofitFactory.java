package org.mt.androidlibrary.net;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import androidx.core.util.Preconditions;
import okhttp3.OkHttpClient;
import org.mt.androidlibrary.BuildConfig;
import org.mt.androidlibrary.net.config.ApiConfig;
import org.mt.androidlibrary.net.https.SslSocketFactory;
import org.mt.androidlibrary.net.https.UnSafeTrustManager;
import org.mt.androidlibrary.net.interceptor.CommonParamInterceptor;
import org.mt.androidlibrary.net.interceptor.HeaderInterceptor;
import org.mt.androidlibrary.net.interceptor.JsonInterceptor;
import org.mt.androidlibrary.net.interceptor.RetryInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Description:Retrofit的工具类
 * @Author:zwna
 * @Date:2019-04-29
 */
@SuppressLint("RestrictedApi")
public class RetrofitFactory {
    private Retrofit.Builder mBuilder;
    private Retrofit mRetrofit;
    private static RetrofitFactory mInstance;

    private RetrofitFactory(ApiConfig apiConfig){
        //初始化
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(apiConfig.getReadTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(apiConfig.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(apiConfig.getWriteTimeout(),TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HeaderInterceptor(apiConfig.getHeads()))
                .addInterceptor(new CommonParamInterceptor(apiConfig.getComnParams()))
                .addInterceptor(new RetryInterceptor(apiConfig.getMaxRetry()));

        //信任https证书
        if (apiConfig.getOpenHttps()){
            httpClientBuilder.sslSocketFactory(SslSocketFactory.getSSLSocketFactory(),new UnSafeTrustManager());
        }
        //开发模式下添加打印
        if (BuildConfig.DEBUG){
            httpClientBuilder.addInterceptor(new JsonInterceptor());
        }
        OkHttpClient httpClient = httpClientBuilder.build();


        mBuilder = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        //默认使用配置的mBaseUrl
        mRetrofit = mBuilder.baseUrl(apiConfig.getBaseUrl()).build();
    }

    /**
     * 单例模式
     * @return
     */
    public static RetrofitFactory getInstance(ApiConfig apiConfig){
        if (mInstance == null){
            synchronized (RetrofitFactory.class){
                if (mInstance == null){
                    mInstance = new RetrofitFactory(apiConfig);
                }
            }
        }
        return mInstance;
    }

    /**
     * 根据Api接口类生成Api实体
     * @param clazz 传入的Api接口类
     * @param <T> Api实体类
     * @return
     */
    public <T> T create(Class<T> clazz){
        Preconditions.checkNotNull(mBuilder,"ApiConfig BaseUrl not init");
        return mRetrofit.create(clazz);
    }

    /**
     * 传人baseUrl
     * 动态生成Api实体
     * @param baseUrl baseUrl
     * @param clazz 传入的Api接口类
     * @param <T> Api实体类
     * @return
     */
    public <T> T create(String baseUrl, Class<T> clazz){
        if (TextUtils.isEmpty("baseUrl")){
            throw new NullPointerException("baseUrl can not be empty");
        }
        return mBuilder.baseUrl(baseUrl).build().create(clazz);
    }
}
