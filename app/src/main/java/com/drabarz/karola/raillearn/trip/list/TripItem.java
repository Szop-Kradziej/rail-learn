package com.drabarz.karola.raillearn.trip.list;

import android.view.View;
import android.widget.TextView;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;

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

    public Trip getTrip() {
        return trip;
    }

    private void setUserName(View view) {
        TextView userNameTextView = (TextView) view.findViewById(R.id.userNameTextView);
        userNameTextView.setText(trip.getUser().getName());
    }

    private void setTripTitle(View view) {
        TextView tripTitleTextView = (TextView) view.findViewById(R.id.tripTitleTextView);
        tripTitleTextView.setText(trip.getOffer().getTitle());
    }

    private void setTripRoute(View view) {
        TextView tripRouteTextView = (TextView) view.findViewById(R.id.tripRouteTextView);
        tripRouteTextView.setText(trip.getRoute().getMainRoute());
    }

    public void setTripDate(View view) {
        TextView tripDateTextView = (TextView) view.findViewById(R.id.tripDateTextView);
        tripDateTextView.setText(trip.getRoute().getDeparture().getDate());
    }

    public void setTripTime(View view) {
        TextView tripTimeTextView = (TextView) view.findViewById(R.id.tripTimeTextView);
        tripTimeTextView.setText(trip.getRoute().getDeparture().getTime());
    }
}
