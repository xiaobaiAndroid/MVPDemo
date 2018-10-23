package com.bzf.mvpdemo.news;

import com.bzf.mvpdemo.base.IPresenter;
import com.bzf.mvpdemo.base.IView;
import com.bzf.mvpdemo.entity.News;

import java.util.List;

public interface NewsContract {

    interface Presenter extends IPresenter{

        /*根据类型获取新闻*/
        void getNews(String type);

    }

    interface View extends IView{

        void getNewsSuccess(List<News.DataBean> list);

        void getNewsFail(String message);

    }
}
