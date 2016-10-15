package com.example.kolin.testapplication.presentation.firststar;

import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.interactor.GetVitalCharacteristicUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

/**
 * Created by kolin on 15.10.2016.
 */

public class FirstStartPresenter extends AbstractPresenter<FirstStartView> {

    private static final String TAG = FirstStartPresenter.class.getSimpleName();

    private GetVitalCharacteristicUC getVitalCharacteristicUC;

    public FirstStartPresenter() {
        getVitalCharacteristicUC = new GetVitalCharacteristicUC();
    }

    public boolean isCorrectEditText(String text){
        if (text.isEmpty() ||  text.equals("")) {
            return false;
        }
        if (Double.valueOf(text) <= 0){
            return false;
        }
        return true;
    }

    public void addMainVitalCharacteristic(VitalCharacteristic vitalCharacteristic){
        getVitalCharacteristicUC.addVitalCharacteristic(vitalCharacteristic);
    }
}
