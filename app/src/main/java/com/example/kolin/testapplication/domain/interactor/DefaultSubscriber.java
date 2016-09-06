package com.example.kolin.testapplication.domain.interactor;

import rx.Subscriber;

/**
 * Created by kolin on 06.09.2016.
 */

public class DefaultSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted(){
        //stub
    }

    @Override
    public void onError(Throwable e) {
        //stub
    }

    @Override
    public void onNext(T t) {
        //stub
    }
}
