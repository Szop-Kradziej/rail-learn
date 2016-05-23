package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;

import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface RailLearnCancelJoinTripApi {
    @POST("/trips/{id}/cancel")
    Observable<Trip> cancelTrip(@Path("id") String id);
}
