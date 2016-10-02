package com.example.kolin.testapplication.data.orm;

import com.example.kolin.testapplication.domain.Food;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by kolin on 14.09.2016.
 */

public class RealmQueries {

    private Realm realmMain;

    public RealmQueries() {
        this.realmMain = RealmMainSingleton.getInstanceMain();
    }

    public void addFavoriteFood(final Food food) {
        realmMain.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(food);
            }
        });
    }

    public Observable<List<Food>> getFavoriteFood() {
        return realmMain
                .where(Food.class)
                .isNotNull("idName")
                .findAll()
                .asObservable()
                .filter(new Func1<RealmResults<Food>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<Food> foods) {
                        return foods.isLoaded();
                    }
                })
                .flatMap(new Func1<RealmResults<Food>, Observable<List<Food>>>() {
                    @Override
                    public Observable<List<Food>> call(RealmResults<Food> foods) {
                        return Observable.from(foods).toList();
                    }
                });
    }

    public void deleteFavoriteFood(final Food food) {
        realmMain.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                food.deleteFromRealm();
            }
        });
    }

}
