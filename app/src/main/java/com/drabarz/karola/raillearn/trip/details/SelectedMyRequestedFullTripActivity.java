package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.RailLearnCancelJoinTripApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SelectedMyRequestedFullTripActivity extends FullTripActivity {
    @Override
    protected String getConfirmButtonText() {
        return getString(R.string.cancel);
    }

    @Override
    protected void onConfirmButtonClicked() {
        RailLearnCancelJoinTripApi retrofitService = ServiceFactory.createRetrofitService(RailLearnCancelJoinTripApi.class, getString(R.string.service_endpoint));
        retrofitService.cancelTrip(trip.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Trip>() {
                    @Override
                    public void call(Trip trip) {
                        TripsActivity.restart(SelectedMyRequestedFullTripActivity.this);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call (Throwable throwable){
                        Log.e("SelectedMyRequestedTrip", "Service subscribe error");
                    }
                });

        TripsActivity.restart(this);
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, SelectedMyRequestedFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
