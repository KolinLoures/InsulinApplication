package com.example.kolin.testapplication.domain.interactor;

import com.example.kolin.testapplication.data.repository.RepositoryImpl;
import com.example.kolin.testapplication.domain.groups.Group;
import com.example.kolin.testapplication.domain.repository.Repository;
import com.example.kolin.testapplication.domain.usecases.CloudUseCase;

import rx.Observable;

/**
 * Created by kolin on 06.09.2016.
 */

public class GetItemsOfGroupUC extends CloudUseCase {

    private final Repository repository;
    @Group.GroupAllFood
    private String groupName;

    public GetItemsOfGroupUC() {
        repository = new RepositoryImpl();
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (!groupName.equals(Group.ALL)) {
            return repository.getRestaurants(this.groupName);
        }
        return repository.getAllItemsOfGroup();
    }

    public void setParameterCategoryName(@Group.GroupAllFood String groupName){
        this.groupName = groupName;
    }
}
