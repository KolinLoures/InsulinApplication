package com.example.kolin.testapplication.data.net;

import com.example.kolin.testapplication.data.entities.FoodCategoryEntity;
import com.example.kolin.testapplication.data.entities.ItemOfGroupEntity;
import com.example.kolin.testapplication.domain.groups.Group;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by kolin on 06.09.2016.
 */

public interface Api {

    @GET("list_group/{group}/.json")
    Observable<List<ItemOfGroupEntity>> getItemsOfGroup(@Path("group")
                                                  @Group.GroupAllFood String group);


    @GET("list_food/{name_item_group}.json")
    Observable<List<FoodCategoryEntity>> getFoodFromItem(@Path("name_item_group")
                                                             String itemName);
}
