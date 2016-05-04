package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface RailLearnCreateTripApi {
    @POST("/trips")
    Observable<Trip> postTrip(@Body Trip trip);
}
