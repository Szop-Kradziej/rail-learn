package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.drabarz.karola.raillearn.model.Route;

public class EditTripRouteActivity extends TripRouteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onConfirmRouteButtonClicked() {
        Route route = getRouteInputData();
        startNewTripOfferActivity(route);
    }

    private void startNewTripOfferActivity(Route route) {
        NewTripOfferActivity.start(this, route);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, EditTripRouteActivity.class);
        context.startActivity(intent);
    }
}
