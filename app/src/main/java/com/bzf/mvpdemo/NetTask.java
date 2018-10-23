package com.bzf.mvpdemo;

import java.util.Observable;

import io.reactivex.observers.DisposableObserver;

public interface NetTask<T> {

    DisposableObserver execute(T data, TasksCallback callback);

}
