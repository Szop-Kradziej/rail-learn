package com.drabarz.karola.raillearn.service;

import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface RailLearnDeleteTripApi {
    @POST("/trips/{id}/delete")
    Observable<String> deleteTrip(@Path("id") String id);
}
