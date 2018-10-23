package com.bzf.mvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bzf.mvpdemo.base.BaseActivity;
import com.bzf.mvpdemo.entity.News;
import com.bzf.mvpdemo.news.NewsContract;
import com.bzf.mvpdemo.news.NewsPresenter;
import com.bzf.mvpdemo.news.NewsTask;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements NewsContract.View {

    private NewsPresenter presenter;
    private TextView contentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTV = findViewById(R.id.contentTV);


        presenter = new NewsPresenter(this, NewsTask.getInstance());
        presenter.getNews("top");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();

    }

    @Override
    public void getNewsSuccess(List<News.DataBean> list) {
        if(list!=null){
            contentTV.setText(Arrays.toString(list.toArray()));
        }

    }

    @Override
    public void getNewsFail(String message) {
        contentTV.setText(message);
    }

}
