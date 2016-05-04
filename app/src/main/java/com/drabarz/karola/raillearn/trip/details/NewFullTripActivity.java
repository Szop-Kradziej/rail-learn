package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.RailLearnCreateTripApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class NewFullTripActivity extends FullTripActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setText(R.string.create);
    }

    @Override
    protected void onConfirmButtonClicked() {
        RailLearnCreateTripApi retrofitService = ServiceFactory.createRetrofitService(RailLearnCreateTripApi.class, getString(R.string.service_endpoint));
        retrofitService.postTrip(trip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Trip>() {
                    @Override
                    public void call(Trip trip) {
                        TripsActivity.restart(NewFullTripActivity.this);
                        Log.i("NewFullTripActivity", trip.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("NewFullTripActivity", "Service subscribe error");
                    }
                });
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, NewFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
