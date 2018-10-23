package com.bzf.mvpdemo.news;

import com.bzf.mvpdemo.entity.News;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 新闻接口
 * @author baizhengfu
 * @version 1.0
 * @since JDK1.8
 * @date 2018/10/23
 */
public interface INews {

    @GET("toutiao/index")
    Observable<News> getNewsList(@Query("type")String type,@Query("key")String APPKEY);
}
