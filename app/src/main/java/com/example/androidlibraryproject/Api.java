package com.example.androidlibraryproject;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.util.HashMap;

/**
 * @Description:
 * @Author:liubiao
 * @Date:2019-04-29
 */
public interface Api {

    @POST("/api/api")
    Observable<WithdrawBean> withdraw(@QueryMap HashMap<String,String> hashMap);
}
