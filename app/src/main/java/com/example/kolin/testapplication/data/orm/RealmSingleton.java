package com.example.kolin.testapplication.data.orm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by kolin on 14.09.2016.
 */

public class RealmSingleton {

    private static Realm instance = null;

    public static Realm initialize(Context context) {
        if (instance == null) {
            Realm.setDefaultConfiguration(new RealmConfiguration.Builder(context)
                    .rxFactory(new RealmObservableFactory())
                    .build());
            instance = Realm.getDefaultInstance();
        }
        return instance;
    }

    public static Realm getInstance() {
        return instance;
    }
}
