package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;

import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RailLearnJoinTripApi {
    @POST("/trips/{trip_id}/join")
    Observable<Trip> joinTrip(@Path("trip_id") String tripId, @Query("user_id") String userIid);
}
