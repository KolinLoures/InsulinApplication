package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.categories.Categories;
import com.example.kolin.testapplication.domain.repository.Repository;

import rx.Observable;

/**
 * Created by kolin on 06.09.2016.
 */

public class GetCategoriesUC extends UseCase {

    private final Repository repository;
    @Categories.Category
    private String categoryName;

    public GetCategoriesUC() {
        repository = new RepositoryImpl();
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getRestaurants(this.categoryName);
    }

    public void setParameterCategoryName(@Categories.Category String categoryName){
        this.categoryName = categoryName;
    }
}
