package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.RailLearnJoinTripApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SelectedFullTripActivity extends FullTripActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setText(R.string.join);
    }

    @Override
    protected void onConfirmButtonClicked() {
        RailLearnJoinTripApi retrofitService = ServiceFactory.createRetrofitService(RailLearnJoinTripApi.class, getString(R.string.service_endpoint));
        retrofitService.joinTrip("0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Trip>() {
                    @Override
                    public void call(Trip trip) {
                        TripsActivity.restart(SelectedFullTripActivity.this);
                        Log.i("SelectFullTripActivity", trip.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("SelectFullTripActivity", "Service subscribe error");
                    }
                });
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, SelectedFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
