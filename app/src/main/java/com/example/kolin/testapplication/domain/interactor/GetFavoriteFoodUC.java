package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.repository.Repository;
import com.example.kolin.testapplication.domain.usecases.ObservableRealmDataUseCase;

import rx.Observable;

/**
 * Created by kolin on 18.09.2016.
 */

public class GetFavoriteFoodUC extends ObservableRealmDataUseCase {

    private Repository repository;

    public GetFavoriteFoodUC() {
        this.repository = new RepositoryImpl();
    }

    @Override
    public Observable buildObservable() {
        return repository.getFavoriteFood();
    }
}
