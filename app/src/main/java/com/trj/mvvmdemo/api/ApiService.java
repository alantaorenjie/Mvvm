package com.trj.mvvmdemo.api;

import com.trj.mvvmdemo.model.GankioData;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by TRJ on 2017/9/6.
 * Description:
 */

public interface ApiService {
    //http://gank.io/api/data/Android/10/1

    @GET("10/1")
    Observable<GankioData> getData();
}
