package com.example.kolin.testapplication.data.net;

import com.example.kolin.testapplication.domain.Restaurant;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by kolin on 06.09.2016.
 */

public interface Api {

    @GET("restaurants.json")
    Observable<List<Restaurant>> getRestaurants();
}
