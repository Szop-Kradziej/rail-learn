package com.drabarz.karola.raillearn.service;

import com.drabarz.karola.raillearn.model.Trip;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface RailLearnService {
    String SERVICE_ENDPOINT = "http://10.9.0.230:8080";

    @GET("/trips")
    Observable <List<Trip>> getTrip();
}
