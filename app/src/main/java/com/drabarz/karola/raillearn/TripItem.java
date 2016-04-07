package com.drabarz.karola.raillearn;

import android.view.View;
import android.widget.TextView;

public class TripItem {

    private final Trip trip;

    public TripItem(Trip trip) {
        this.trip = trip;
    }

    public void bindLayout(View view) {
        setUserName(view);
        setTripTitle(view);
        setTripRoute(view);
        setTripDate(view);
        setTripTime(view);
    }

    private void setUserName(View view) {
        TextView userNameTextView = (TextView) view.findViewById(R.id.userNameTextView);
        userNameTextView.setText(trip.getUserName());
    }

    private void setTripTitle(View view) {
        TextView tripTitleTextView = (TextView) view.findViewById(R.id.tripTitleTextView);
        tripTitleTextView.setText(trip.getTripTitle());
    }

    private void setTripRoute(View view) {
        TextView tripRouteTextView = (TextView) view.findViewById(R.id.tripRouteTextView);
        tripRouteTextView.setText(trip.getTripRoute());
    }

    public void setTripDate(View view) {
        TextView tripDateTextView = (TextView) view.findViewById(R.id.tripDateTextView);
        tripDateTextView.setText(trip.getTripDate());
    }

    public void setTripTime(View view) {
        TextView tripTimeTextView = (TextView) view.findViewById(R.id.tripTimeTextView);
        tripTimeTextView.setText(trip.getTripTime());
    }
}
