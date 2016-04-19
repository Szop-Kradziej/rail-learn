package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.drabarz.karola.raillearn.R;

public class NewTripRouteActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_trip_route_activity);

        addDateTextListener();
        addTimeTextListener();
    }

    private void addDateTextListener() {
        EditText inputStationDateEditText = (EditText) findViewById(R.id.inputStationDateEditText);
        inputStationDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DatePicker datePicker = new DatePicker();
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }

    private void addTimeTextListener() {
        EditText inputStationTimeEditText = (EditText) findViewById(R.id.inputStationTimeEditText);
        inputStationTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });
    }

    private void showTimePickerDialog(View v) {
        TimePicker timePicker = new TimePicker();
        timePicker.show(getSupportFragmentManager(), "timePicker");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NewTripRouteActivity.class);
        context.startActivity(intent);
    }
}
