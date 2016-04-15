package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface RailLearnService {
    @GET("/trips")
    Observable<List<Trip>> getTrip();
}
