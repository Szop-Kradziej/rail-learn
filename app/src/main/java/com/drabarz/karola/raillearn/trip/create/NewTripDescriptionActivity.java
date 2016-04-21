package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.drabarz.karola.raillearn.R;

public class NewTripDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_trip_description_activity);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NewTripDescriptionActivity.class);
        context.startActivity(intent);
    }
}
