package com.example.kolin.testapplication.presentation.products.catalog.selection;

import com.example.kolin.testapplication.domain.Restaurant;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetRestaurantsUC;
import com.example.kolin.testapplication.presentation.AbstractPresenter;

import java.util.List;

/**
 * Created by kolin on 06.09.2016.
 */

public class SelectionPresenter extends AbstractPresenter<SelectionContract.View> {

    private GetRestaurantsUC getRestaurantsUC;

    public SelectionPresenter() {
        getRestaurantsUC = new GetRestaurantsUC();
    }


    public void loadRestaurants(List<Restaurant> restaurants) {
        if (isViewAttach()) {
            getWeakReference().showLoadedRestaurants(restaurants);
        }
    }


    public void load() {
        getRestaurantsUC.execute(new SelectionSubscriber());
    }


    private final class SelectionSubscriber extends DefaultSubscriber<List<Restaurant>> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<Restaurant> list) {
            SelectionPresenter.this.loadRestaurants(list);
        }
    }

    @Override
    public void attachView(SelectionContract.View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    protected boolean isViewAttach() {
        return super.isViewAttach();
    }

    @Override
    protected SelectionContract.View getWeakReference() {
        return super.getWeakReference();
    }
}
