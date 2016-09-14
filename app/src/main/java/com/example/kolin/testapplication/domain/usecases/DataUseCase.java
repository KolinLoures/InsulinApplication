package com.example.kolin.testapplication.domain.usecases;

/**
 * Created by kolin on 14.09.2016.
 */

public abstract class DataUseCase<T> {
    public abstract void execute(T t);
}
