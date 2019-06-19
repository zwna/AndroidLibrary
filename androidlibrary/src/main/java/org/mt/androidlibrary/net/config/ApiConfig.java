package org.mt.androidlibrary.net.config;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Description:Retrofit相关的配置类
 * @Author:zwna
 * @Date:2019-04-29
 */
public class ApiConfig implements Serializable {
    //retrofit的baseUrl
    private String mBaseUrl;
    //写入超时
    private int mWriteTimeout;
    //读取超时
    private int mReadTimeout;
    //连接超时
    private int mConnectTimeout;
    //当网络请求失败的时候的重新连接的次数
    private int mMaxRetry = 5;
    //公共头参数
    private static ArrayMap<String, String> mHeads;
    //公共参数
    private static HashMap<String, String> mCommonParams = new HashMap<>();
    //是否信任https证书 true:信任https证书 false:不信任https证书
    private static boolean mOpenHttps;

    private ApiConfig(Builder builder){
        mBaseUrl = builder.baseUrl;
        mWriteTimeout = builder.writeTimeout;
        mReadTimeout = builder.readTimeout;
        mConnectTimeout = builder.connectTimeout;
        mMaxRetry = builder.maxRetry;
        mHeads = builder.heads;
        mCommonParams = builder.comnParams;
        mOpenHttps = builder.openHttps;

        //添加接口公共参数
        if (null == mCommonParams){
            mCommonParams = new HashMap<>();
        }
    }

    public int getMaxRetry() {
        return mMaxRetry;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public int getReadTimeout() {
        return mReadTimeout;
    }

    public int getWriteTimeout() {
        return mWriteTimeout;
    }

    public int getConnectTimeout() {
        return mConnectTimeout;
    }

    public ArrayMap<String, String> getHeads() {
        return mHeads;
    }

    public void setHeads(ArrayMap<String, String> mHeads) {
        ApiConfig.mHeads = mHeads;
    }

    public HashMap<String, String> getComnParams() {
        return mCommonParams;
    }

    public void setComnParams(HashMap<String, String> mCommonParams) {
        ApiConfig.mCommonParams = mCommonParams;
    }

    public boolean getOpenHttps() {
        return mOpenHttps;
    }

    public static final class Builder {
        private String baseUrl;
        private int writeTimeout;
        private int readTimeout;
        private int connectTimeout;
        private int maxRetry = 2;
        private ArrayMap<String, String> heads;
        private HashMap<String, String> comnParams;
        private boolean openHttps;

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setMaxRetry(int maxRetry) {
            this.maxRetry = maxRetry;
            return this;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setHeads(ArrayMap<String, String> heads) {
            this.heads = heads;
            return this;
        }

        public Builder setComnParams(HashMap<String, String> params) {
            this.comnParams = params;
            return this;
        }

        public Builder setOpenHttps(boolean openHttps) {
            this.openHttps = openHttps;
            return this;
        }

        public ApiConfig build(){
            return new ApiConfig(this);
        }
    }
}
