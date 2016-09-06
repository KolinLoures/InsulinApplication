package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.repository.Repository;

import rx.Observable;

/**
 * Created by kolin on 06.09.2016.
 */

public class GetRestaurantsUC extends UseCase {

    private final Repository repository;

    public GetRestaurantsUC() {
        repository = new RepositoryImpl();
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getRestaurants();
    }
}
