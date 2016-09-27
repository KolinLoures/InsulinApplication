package com.example.kolin.testapplication;

import android.app.Application;

import com.example.kolin.testapplication.data.orm.RealmSingleton;
import com.facebook.stetho.Stetho;

/**
 * Created by kolin on 14.09.2016.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmSingleton.initialize(getApplicationContext());
        Stetho.initializeWithDefaults(this);
    }
}
