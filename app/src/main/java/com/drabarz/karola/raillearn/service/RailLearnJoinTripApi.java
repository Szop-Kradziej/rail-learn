package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.orange.SmsResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RailLearnJoinTripApi {
    @POST("/trips/{trip_id}/join")
    Observable<Trip> joinTrip(@Path("trip_id") String tripId, @Query("user_id") String userId);

    @GET("/orange/oracle/sendsms?to=48666666666&from=Orange&msg=YourOfferIsAccepted")
    Observable<SmsResponse> sendSms();
}
