package com.example.kolin.testapplication.presentation.index.addingdialog;

import android.util.Log;

import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.interactor.GetVitalCharacteristicUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

/**
 * Created by kolin on 10.10.2016.
 */

public class AddIndexDialogPresenter extends AbstractPresenter<AddIndexDialogView> {

    private static final String TAG = AddIndexDialogPresenter.class.getSimpleName();

    private GetVitalCharacteristicUC getVitalCharacteristicUC;

    public AddIndexDialogPresenter() {
        getVitalCharacteristicUC = new GetVitalCharacteristicUC();
    }

    public void addVitalCharacteristic(VitalCharacteristic vitalCharacteristic) {
        getVitalCharacteristicUC.addVitalCharacteristic(vitalCharacteristic);

        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
            return;
        }

        getWeakReference().showToast("Настройки добавлены!");
    }


    public boolean isCorrectEditText(String text) {
        if (text.isEmpty() ||  text.equals("")) {
            return false;
        }
        if (Double.valueOf(text) == 0){
            return false;
        }
        return true;
    }

}
