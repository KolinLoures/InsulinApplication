package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.repository.Repository;
import com.example.kolin.testapplication.domain.usecases.ObservableRealmDataUseCase;

import rx.Observable;

/**
 * Created by kolin on 10.10.2016.
 */

public class GetVitalCharacteristicUC extends ObservableRealmDataUseCase {

    private Repository repository;

    public GetVitalCharacteristicUC() {
        this.repository = new RepositoryImpl();
    }

    @Override
    public Observable buildObservable() {
        return repository.getVitalCharacteristic();
    }

    public void addVitalCharacteristic(VitalCharacteristic vitalCharacteristic){
        repository.addVitalCharacteristic(vitalCharacteristic);
    }

    public void deleteVitalCharacteristic(VitalCharacteristic vitalCharacteristic){
        repository.deleteVitalCharacteristic(vitalCharacteristic);
    }
}
