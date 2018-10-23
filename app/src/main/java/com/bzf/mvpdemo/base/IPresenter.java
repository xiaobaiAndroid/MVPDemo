package com.bzf.mvpdemo.base;

import io.reactivex.observers.DisposableObserver;

public interface IPresenter {

    void subscribe(DisposableObserver disposableObserver);
    void unsubscribe();
}
