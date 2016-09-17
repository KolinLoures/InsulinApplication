package com.example.kolin.testapplication.presentation.products.catalog.favoritelist;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetFavoriteFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.List;

/**
 * Created by kolin on 18.09.2016.
 */

public class FavoritePresenter extends AbstractPresenter<FavoriteView> {

    private static final String TAG = FavoritePresenter.class.getSimpleName();

    private GetFavoriteFoodUC getFavoriteFoodUC;


    public FavoritePresenter() {
        this.getFavoriteFoodUC = new GetFavoriteFoodUC();
    }

    public void load(){
        getFavoriteFoodUC.execute(new FavoriteSubscriber());
    }

    public void showLoadedData(List<Food> foodList){
        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showFavoriteFood(foodList);
    }

    private final class FavoriteSubscriber extends DefaultSubscriber<List<Food>>{
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Food> list) {
            FavoritePresenter.this.showLoadedData(list);
        }
    }

    public void unsubscribe(){
        getFavoriteFoodUC.unsubscribe();
    }
}
