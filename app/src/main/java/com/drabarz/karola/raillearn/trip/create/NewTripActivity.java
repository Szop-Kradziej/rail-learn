package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.drabarz.karola.raillearn.R;

public class NewTripActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_trip_activity);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NewTripActivity.class);
        context.startActivity(intent);
    }
}
