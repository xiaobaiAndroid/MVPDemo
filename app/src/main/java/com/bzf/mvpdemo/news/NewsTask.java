package com.bzf.mvpdemo.news;

import com.bzf.mvpdemo.NetTask;
import com.bzf.mvpdemo.TasksCallback;
import com.bzf.mvpdemo.entity.News;
import com.bzf.mvpdemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bzf.mvpdemo.entity.News.DataBean;

/**
 * 新闻任务
 * @author baizhengfu
 * @version 1.0
 * @since JDK1.8
 * @date 2018/10/23
 */
public class NewsTask implements NetTask<String> {

    private static final String HOST = "http://v.juhe.cn";
    private static final String APPKEY = "77352597cb2a7d8c308da20889d2de27";
    private Retrofit retrofit;

    private NewsTask(){
        retrofit = new Retrofit.Builder().baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static NewsTask getInstance(){
        return SingletonHolder.INSTATNCE;
    }

    /*在第一调用时，才会加载到虚拟机进行实例化*/
    private static class SingletonHolder{
        private static final NewsTask INSTATNCE = new NewsTask();
    }

    
    @Override
    public DisposableObserver execute(String data, final TasksCallback callback) {
        INews iNews = retrofit.create(INews.class);
        DisposableObserver<List<DataBean>> disposableObserver = iNews.getNewsList(data, APPKEY)
                .map(new Function<News, List<DataBean>>() {
                    @Override
                    public List<DataBean> apply(News news) throws Exception {
                        List<DataBean> list = new ArrayList<>();
                        if ("1".equals(news.getResult().getStat())) {
                            list = news.getResult().getData();
                        }
                        LogUtils.logI(Thread.currentThread().getName());
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<DataBean>>() {
                    @Override
                    public void onNext(List<DataBean> list) {
                        LogUtils.logI(Thread.currentThread().getName());
                        callback.onSuccess(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail();
                    }

                    @Override
                    public void onComplete() {
                        callback.onFinish();
                    }
                });
        return disposableObserver;
    }
}
