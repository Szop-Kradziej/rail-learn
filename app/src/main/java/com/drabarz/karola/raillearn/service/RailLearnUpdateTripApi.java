package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface RailLearnUpdateTripApi {
    @POST("/trips/update")
    Observable<Trip> updateTrip(@Body Trip trip);
}
