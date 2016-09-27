package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.repository.Repository;
import com.example.kolin.testapplication.domain.usecases.CloudUseCase;

import rx.Observable;

/**
 * Created by kolin on 27.09.2016.
 */

public class GetCalcFoodUC extends CloudUseCase {

    private Repository repository;

    public GetCalcFoodUC() {
        this.repository = new RepositoryImpl();
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getFoodCalc();
    }


}
