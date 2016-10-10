package com.example.kolin.testapplication.data.orm;

import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.VitalCharacteristic;

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

    public void addResultCalculatedFood(final CalculatedFood calculatedFood) {
        realmMain.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(calculatedFood);
            }
        });
    }

    public Observable<List<CalculatedFood>> getResultCalculatedFood() {
        return realmMain
                .where(CalculatedFood.class)
                .isNotNull("id")
                .findAll()
                .asObservable()
                .filter(new Func1<RealmResults<CalculatedFood>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<CalculatedFood> foods) {
                        return foods.isLoaded();
                    }
                })
                .flatMap(new Func1<RealmResults<CalculatedFood>, Observable<List<CalculatedFood>>>() {
                    @Override
                    public Observable<List<CalculatedFood>> call(RealmResults<CalculatedFood> foods) {
                        return Observable.from(foods).toList();
                    }
                });
    }


    public void deleteResultCalculatedFood(final CalculatedFood calculatedFood) {
        realmMain.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                calculatedFood.deleteFromRealm();
            }
        });
    }

    public void addVitalCharacteristic(final VitalCharacteristic vitalCharacteristic) {
        realmMain.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(vitalCharacteristic);
            }
        });
    }

    public Observable<List<VitalCharacteristic>> getVitalCharacteristic() {
        return realmMain
                .where(VitalCharacteristic.class)
                .isNotNull("name")
                .findAll()
                .asObservable()
                .filter(new Func1<RealmResults<VitalCharacteristic>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<VitalCharacteristic> vitalCharacteristics) {
                        return vitalCharacteristics.isLoaded();
                    }
                })
                .flatMap(new Func1<RealmResults<VitalCharacteristic>, Observable<List<VitalCharacteristic>>>() {
                    @Override
                    public Observable<List<VitalCharacteristic>> call(RealmResults<VitalCharacteristic> vitalCharacteristics) {
                        return Observable.from(vitalCharacteristics).toList();
                    }
                });
    }


    public void deleteVitalCharacteristic(final VitalCharacteristic vitalCharacteristic) {
        realmMain.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                vitalCharacteristic.deleteFromRealm();
            }
        });
    }
}
