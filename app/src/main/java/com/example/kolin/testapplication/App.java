package com.example.kolin.testapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;

/**
 * Created by kolin on 14.09.2016.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Stetho.initializeWithDefaults(this);
    }
}
