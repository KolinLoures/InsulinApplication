package com.example.kolin.testapplication.data.net;

import com.example.kolin.testapplication.domain.ItemCategory;
import com.example.kolin.testapplication.domain.categories.Categories;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by kolin on 06.09.2016.
 */

public interface Api {

    @GET("list_categories/{category}/.json")
    Observable<List<ItemCategory>> getRestaurants(@Path("category")
                                                  @Categories.Category String category);
}
