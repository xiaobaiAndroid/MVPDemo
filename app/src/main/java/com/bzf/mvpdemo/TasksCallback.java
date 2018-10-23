package com.bzf.mvpdemo;

public interface TasksCallback<T> {

    void onSuccess(T t);

    void onFail();

    void onFinish();

}
