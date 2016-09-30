package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.repository.Repository;
import com.example.kolin.testapplication.domain.usecases.DataUseCase;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by kolin on 30.09.2016.
 */

public class GetObservableCalculatedFoodUC extends DataUseCase<Subscriber> {

    private Repository repository;
    private Subscription subscription = Subscriptions.empty();

    public GetObservableCalculatedFoodUC() {
        this.repository = new RepositoryImpl();
    }

    public void addCalculationFood(Food food) {
        repository.addCalculationFood(food);
    }

    public void deleteCalculationFood(Food food) {
        repository.deleteCalculationFood(food);
    }

    public void clearCalculationFood() {
        repository.deleteAllFromCalculation();
    }

    @Override
    public void execute(Subscriber subscriber) {
        repository.getCalculationFood()
                .subscribe(subscriber);
    }

    public void unsubscribe(){
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


}
