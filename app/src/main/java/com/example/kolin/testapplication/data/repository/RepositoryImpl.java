package com.example.kolin.testapplication.data.repository;

import com.example.kolin.testapplication.data.net.Api;
import com.example.kolin.testapplication.data.net.RetrofitSingleton;
import com.example.kolin.testapplication.domain.Restaurant;
import com.example.kolin.testapplication.domain.repository.Repository;

import java.util.List;

import rx.Observable;

/**
 * Created by kolin on 06.09.2016.
 */

public class RepositoryImpl implements Repository {

    private Api api;

    public RepositoryImpl() {
        api = RetrofitSingleton.getApi();
    }

    @Override
    public Observable<List<Restaurant>> getRestaurants() {
        return api.getRestaurants();
    }
}
