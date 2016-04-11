package com.drabarz.karola.raillearn.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        return createRetrofit(endPoint).create(clazz);
    }

    private static Retrofit createRetrofit(String endPoint) {
        return new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
