package com.example.kolin.testapplication.presentation.index;

import com.example.kolin.testapplication.domain.VitalCharacteristic;

import java.util.List;

/**
 * Created by kolin on 10.10.2016.
 */

public interface IndexView {

    void showLoadedVitalCharacteristic(List<VitalCharacteristic> list);

    void showSnackBar(String title);
}
