package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import com.drabarz.karola.raillearn.model.Route;

public class NewTripRouteActivity extends TripRouteActivity{

    @Override
    protected void onConfirmRouteButtonClicked() {
        Route route = getRouteInputData();
        startNewTripOfferActivity(route);
    }

    private void startNewTripOfferActivity(Route route) {
        NewTripOfferActivity.start(this, route);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NewTripRouteActivity.class);
        context.startActivity(intent);
    }
}
