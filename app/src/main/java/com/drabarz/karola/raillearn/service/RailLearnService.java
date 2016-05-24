package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RailLearnService {
    @GET("/trips")
    Observable<List<Trip>> getTrip(@Query("user_id") String userId);
}
