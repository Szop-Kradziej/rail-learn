package com.drabarz.karola.raillearn.trip.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;

public abstract class FullTripActivity extends AppCompatActivity {

    protected static final String TRIP = "trip";
    protected Trip trip;
    protected abstract String getConfirmButtonText();

    protected abstract void onConfirmButtonClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_trip_activity);
        bindLayout();

        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setText(getConfirmButtonText());
        addConfirmButtonListener();
    }

    private void addConfirmButtonListener() {
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmButtonClicked();
            }
        });
    }

    private void bindLayout() {
        trip = (Trip) getIntent().getSerializableExtra(TRIP);
        setTripTitle(trip.getOffer().getTitle());
        setMainTripRoute(trip.getRoute().getMainRoute());
        setTripDate(trip.getRoute().getDeparture().getDate());
        setTripTime(trip.getRoute().getDeparture().getTime());
        setUserName(trip.getUser().getName());
        setTripDescription(trip.getOffer().getDescription());
    }

    private void setTripTitle(String tripTitle) {
        TextView tripTitleTextView = (TextView) findViewById(R.id.tripTitleTextView);
        tripTitleTextView.setText(tripTitle);
    }

    private void setMainTripRoute(String mainRoute) {
        TextView mainRouteTextView = (TextView) findViewById(R.id.tripRouteTextView);
        mainRouteTextView.setText(mainRoute);
    }

    private void setTripDate(String tripDate) {
        TextView tripDateTextView = (TextView) findViewById(R.id.tripDateTextView);
        tripDateTextView.setText(tripDate);
    }

    private void setTripTime(String tripTime) {
        TextView tripTimeTextView = (TextView) findViewById(R.id.tripTimeTextView);
        tripTimeTextView.setText(tripTime);
    }

    private void setUserName(String userName) {
        TextView userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        userNameTextView.setText(userName);
    }

    public void setTripDescription(String tripDescription) {
        TextView tripDescriptionTextView = (TextView) findViewById(R.id.tripDescriptionTextView);
        tripDescriptionTextView.setText(tripDescription);
    }
}
