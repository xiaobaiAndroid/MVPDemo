package com.bzf.mvpdemo.news;

import com.bzf.mvpdemo.NetTask;
import com.bzf.mvpdemo.TasksCallback;
import com.bzf.mvpdemo.entity.News;

import java.util.List;
import java.util.Observable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View view;
    private NetTask task;
    private CompositeDisposable disposable;

    public NewsPresenter(NewsContract.View view, NetTask task) {
        this.view = view;
        this.task = task;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getNews(String type) {
        DisposableObserver disposableObserver = task.execute(type, new TasksCallback<List<News.DataBean>>() {

            @Override
            public void onSuccess(List<News.DataBean> news) {
                if (view != null) {
                    view.getNewsSuccess(news);
                }
            }

            @Override
            public void onFail() {
                if (view != null) {
                    view.getNewsFail("获取失败");
                }
            }

            @Override
            public void onFinish() {

            }
        });
        subscribe(disposableObserver);
    }

    @Override
    public void subscribe(DisposableObserver disposableObserver) {
        disposable.add(disposableObserver);
    }

    @Override
    public void unsubscribe() {
        disposable.clear();
        view  =null;
    }
}
