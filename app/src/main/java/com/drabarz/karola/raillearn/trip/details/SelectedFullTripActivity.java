package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.protocol.JoinTripRequest;

public class SelectedFullTripActivity extends FullTripActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setText(R.string.join);
    }

    @Override
    protected void onConfirmButtonClicked() {
        //TODO: Send request to server
        String json = new JoinTripRequest().getJson();
        Log.i("SelectedFullTrip", json);
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, SelectedFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
