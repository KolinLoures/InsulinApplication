package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.repository.Repository;
import com.example.kolin.testapplication.domain.usecases.ObservableRealmDataUseCase;

import rx.Observable;

/**
 * Created by kolin on 10.10.2016.
 */

public class GetResultCalculationUC extends ObservableRealmDataUseCase {

    private Repository repository;

    public GetResultCalculationUC() {
        repository = new RepositoryImpl();
    }

    @Override
    public Observable buildObservable() {
        return repository.getResultCalculatedFood();
    }


    public void deleteResultCalculatedFood(CalculatedFood calculatedFood){
        repository.deleteResultCalculatedFood(calculatedFood);
    }

    public void addResultCalculatedFood(CalculatedFood calculatedFood){
        repository.addResultCalculatedFood(calculatedFood);
    }
}
