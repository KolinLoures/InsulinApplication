package com.example.kolin.testapplication.presentation.index;

import android.util.Log;

import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetVitalCharacteristicUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.List;

/**
 * Created by kolin on 10.10.2016.
 */

public class IndexPresenter extends AbstractPresenter<IndexView> {

    private static final String TAG = IndexPresenter.class.getSimpleName();

    private GetVitalCharacteristicUC getVitalCharacteristicUC;



    public IndexPresenter() {
        getVitalCharacteristicUC = new GetVitalCharacteristicUC();
    }

    public void load(){
        getVitalCharacteristicUC.execute(new IndexSubscriber());
    }

    private final class IndexSubscriber extends DefaultSubscriber<List<VitalCharacteristic>>{
        @Override
        public void onNext(List<VitalCharacteristic> list) {
            showLoadedVitalCharacteristic(list);
        }
    }

    public void showLoadedVitalCharacteristic(List<VitalCharacteristic> list){
        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showLoadedVitalCharacteristic(list);
    }

    public void deleteVitalCharacteristic(VitalCharacteristic vitalCharacteristic){
        getVitalCharacteristicUC.deleteVitalCharacteristic(vitalCharacteristic);

        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showSnackBar("Удалено из настроек!");
    }
}
