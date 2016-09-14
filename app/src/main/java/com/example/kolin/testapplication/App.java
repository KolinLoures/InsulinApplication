package com.example.kolin.testapplication;

import android.app.Application;

import com.example.kolin.testapplication.data.orm.RealmSingleton;

/**
 * Created by kolin on 14.09.2016.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmSingleton.initialize(getApplicationContext());
    }
}
