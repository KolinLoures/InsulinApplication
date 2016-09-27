package com.example.kolin.testapplication.presentation.products.catalog.dialog;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.DeleteCalcFoodUC;
import com.example.kolin.testapplication.domain.interactor.GetCalcFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.List;

/**
 * Created by kolin on 22.09.2016.
 */

public class DialogPresenter extends AbstractPresenter<DialogView> {

    private static final String TAG = DialogPresenter.class.getSimpleName();

    private GetCalcFoodUC getCalcFoodUC;
    private DeleteCalcFoodUC deleteCalcFoodUC;

    public DialogPresenter() {
        getCalcFoodUC = new GetCalcFoodUC();
        deleteCalcFoodUC = new DeleteCalcFoodUC();
    }

    public void load() {
        getCalcFoodUC.execute(new DialogSubscriber());
    }

    private final class DialogSubscriber extends DefaultSubscriber<List<Food>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Food> foods) {
           DialogPresenter.this.showLoadedData(foods);
        }
    }

    private void showLoadedData(List<Food> foodList) {
        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showLoadedData(foodList);

    }

    public void unSubscribe(){
        getCalcFoodUC.unsubscribe();
    }

    public void deleteFoodFromCalc(Food food){
        deleteCalcFoodUC.execute(food);
    }
}
