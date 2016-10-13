package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.Food;
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

    public void addFavoriteFood(Food food){
        repository.addFoodToFavorite(food);
    }

    public void deleteFavoriteFood(Food food){
        repository.deleteFavoriteFood(food);
    }
}
