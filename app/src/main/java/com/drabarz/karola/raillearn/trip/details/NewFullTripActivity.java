package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;

public class NewFullTripActivity extends FullTripActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setText(R.string.create);
    }

    @Override
    protected void onConfirmButtonClicked() {
        //TODO: Create new trip json and send request to server
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, NewFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
