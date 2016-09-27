package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.repository.Repository;
import com.example.kolin.testapplication.domain.usecases.DataUseCase;

/**
 * Created by kolin on 27.09.2016.
 */

public class DeleteCalcFoodUC extends DataUseCase<Food> {

    private Repository repository;

    public DeleteCalcFoodUC() {
        this.repository = new RepositoryImpl();
    }

    @Override
    public void execute(Food food) {
        repository.deleteFoodFromCalc(food);
    }
}
