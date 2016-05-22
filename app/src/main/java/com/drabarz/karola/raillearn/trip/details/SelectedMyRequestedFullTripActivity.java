package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;

public class SelectedMyRequestedFullTripActivity extends FullTripActivity {
    @Override
    protected String getConfirmButtonText() {
        return getString(R.string.cancel);
    }

    @Override
    protected void onConfirmButtonClicked() {
        TripsActivity.restart(this);
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, SelectedMyRequestedFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
