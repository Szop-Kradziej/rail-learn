package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.trip.create.EditTripRouteActivity;

public class SelectedMyFullTripActivity extends FullTripActivity {

    @Override
    protected String getConfirmButtonText() {
        return getString(R.string.edit);
    }

    @Override
    protected void onConfirmButtonClicked() {
        EditTripRouteActivity.start(this, trip);
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, SelectedMyFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
