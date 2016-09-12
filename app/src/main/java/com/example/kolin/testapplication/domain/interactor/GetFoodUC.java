package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.repository.Repository;

import rx.Observable;

/**
 * Created by kolin on 12.09.2016.
 */

public class GetFoodUC extends UseCase {

    private final Repository repository;
    private String itemGroupName;

    public GetFoodUC() {
        this.repository = new RepositoryImpl();
    }


    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getFood(this.itemGroupName);
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }
}
