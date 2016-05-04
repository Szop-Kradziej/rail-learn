package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;

import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface RailLearnJoinTripApi {
    @POST("/trips/{id}/join")
    Observable<Trip> joinTrip(@Path("id") String id);
}
