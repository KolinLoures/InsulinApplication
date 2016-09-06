package com.example.kolin.testapplication.data.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kolin on 06.09.2016.
 */

public class RetrofitSingleton {

    private static final String URL = "https://testapplication-bb8a1.firebaseio.com/";
    private static Retrofit retrofit = null;
    private static Api api;
    private static OkHttpClient client;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private RetrofitSingleton() {
    }

    public static  void initApi(){
        if (api == null){
            synchronized (RetrofitSingleton.class){
                api = getInstance().create(Api.class);
            }
        }
    }

    public static Api getApi(){
        initApi();
        return api;
    }
}
