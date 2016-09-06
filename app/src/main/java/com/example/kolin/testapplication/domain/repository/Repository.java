package com.example.kolin.testapplication.domain.repository;

import com.example.kolin.testapplication.domain.Restaurant;

import java.util.List;

import rx.Observable;


/**
 * Created by kolin on 06.09.2016.
 */

public interface Repository {

    Observable<List<Restaurant>> getRestaurants();

}
