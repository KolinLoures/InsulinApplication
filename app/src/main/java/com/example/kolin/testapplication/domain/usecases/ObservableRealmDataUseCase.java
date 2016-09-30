package com.example.kolin.testapplication.domain.usecases;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by kolin on 18.09.2016.
 */

public abstract class ObservableRealmDataUseCase extends DataUseCase<Subscriber> implements UseCase {

    public Subscription subscription = Subscriptions.empty();

    public ObservableRealmDataUseCase() {
    }

    public abstract Observable buildObservable();

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Subscriber subscriber) {
        subscription = buildObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
