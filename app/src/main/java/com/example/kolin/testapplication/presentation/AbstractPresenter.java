package com.example.kolin.testapplication.presentation;

import java.lang.ref.WeakReference;

/**
 * Created by kolin on 06.09.2016.
 */

public class AbstractPresenter<T>  {

    private WeakReference<T> weakReference = null;

    public void attachView(T view){
        weakReference = new WeakReference<>(view);
    }

    public void detachView(){
        if (weakReference != null){
            weakReference.clear();
            weakReference = null;
        }
    }

    protected boolean isViewAttach(){
        return weakReference.get() != null && weakReference != null;
    }

    protected T getWeakReference() {
        return weakReference == null ? null : weakReference.get();
    }
}
