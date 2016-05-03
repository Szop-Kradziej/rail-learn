package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Departure;
import com.drabarz.karola.raillearn.model.Route;

public class NewTripRouteActivity extends AppCompatActivity{

    Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_trip_route_activity);

        addDateTextListener();
        addTimeTextListener();
        addConfirmRouteButtonListener();
    }

    private void addDateTextListener() {
        EditText inputStationDateEditText = (EditText) findViewById(R.id.inputStationDateEditText);
        inputStationDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    public void showDatePickerDialog() {
        DatePicker datePicker = new DatePicker();
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }

    private void addTimeTextListener() {
        EditText inputStationTimeEditText = (EditText) findViewById(R.id.inputStationTimeEditText);
        inputStationTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
    }

    private void showTimePickerDialog() {
        TimePicker timePicker = new TimePicker();
        timePicker.show(getSupportFragmentManager(), "timePicker");
    }

    private void addConfirmRouteButtonListener() {
        Button confirmRouteButton = (Button) findViewById(R.id.confirmRouteButton);
        confirmRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmRouteButtonClicked();
            }
        });
    }

    private void onConfirmRouteButtonClicked() {
        setRouteInputData();
        startNewTripOfferActivity(route);
    }

    private void setRouteInputData() {
        Departure departure = getDepartureInputData();
        String arrival = TestData.getArrival();
        route = new Route(departure, arrival);
    }

    public Departure getDepartureInputData() {
        String name = ((EditText) findViewById(R.id.inputStationNameEditText)).getText().toString();
        String date = ((EditText) findViewById(R.id.inputStationDateEditText)).getText().toString();
        String time = ((EditText) findViewById(R.id.inputStationTimeEditText)).getText().toString();

        return new Departure(name, date, time);
    }

    private void startNewTripOfferActivity(Route route) {
        NewTripOfferActivity.start(this, route);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NewTripRouteActivity.class);
        context.startActivity(intent);
    }
}
