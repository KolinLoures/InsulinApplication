package com.example.kolin.testapplication.data.orm;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by kolin on 14.09.2016.
 */

public class RealmMainSingleton {

    private static Realm instanceMain = null;


    public static Realm getInstanceMain() {
        if (instanceMain == null) {
            RealmConfiguration build = new RealmConfiguration
                    .Builder()
                    .build();
            Realm.setDefaultConfiguration(build);
            instanceMain = Realm.getDefaultInstance();
        }
        return instanceMain;
    }

    private RealmMainSingleton() {
    }
}
