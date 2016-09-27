package com.example.kolin.testapplication.data.orm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by kolin on 14.09.2016.
 */

public class RealmSingleton {

    private static Realm instance = null;

    public static Realm initialize(Context context) {
        if (instance == null) {
            RealmConfiguration build = new RealmConfiguration
                    .Builder(context)
                    .build();
            Realm.setDefaultConfiguration(build);
            instance = Realm.getInstance(build);
        }
        return instance;
    }

    public static Realm getInstance() {
        return instance;
    }

    private RealmSingleton() {
    }
}
