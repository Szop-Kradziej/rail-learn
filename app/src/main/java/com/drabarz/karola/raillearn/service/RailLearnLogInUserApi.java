package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Token;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface RailLearnLogInUserApi {
        @POST("/user/login")
        Observable<Token> logInUser(@Query("token") String token);
}
