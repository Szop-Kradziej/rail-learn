package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;

public class FullTripActivity extends AppCompatActivity {

    public static final String TRIP = "trip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_trip_activity);

        bindLayout();
    }

    private void bindLayout() {
        Trip trip = (Trip) getIntent().getSerializableExtra(TRIP);
        setTripTitle(trip.getOffer().getTitle());
        setMainTripRoute(trip.getRoute().getMainRoute());
        setTripDate(trip.getRoute().getDeparture().getDate());
        setTripTime(trip.getRoute().getDeparture().getTime());
        setUserName(trip.getUser().getName());
        setTripStops(trip.getRoute().getStops());
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

    private void setTripStops(String tripStops) {
        TextView tripStopsTextView = (TextView) findViewById(R.id.tripStopsTextView);
        tripStopsTextView.setText(tripStops);
    }

    public void setTripDescription(String tripDescription) {
        TextView tripDescriptionTextView = (TextView) findViewById(R.id.tripDescriptionTextView);
        tripDescriptionTextView.setText(tripDescription);
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, FullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
