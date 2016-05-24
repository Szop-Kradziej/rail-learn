package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.RailLearnUpdateTripApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UpdatedFullTripActivity extends FullTripActivity {

    @Override
    protected String getConfirmButtonText() {
        return getString(R.string.update);
    }

    @Override
    protected void onConfirmButtonClicked() {
        RailLearnUpdateTripApi retrofitService = ServiceFactory.createRetrofitService(RailLearnUpdateTripApi.class, getString(R.string.service_endpoint));
        retrofitService.updateTrip(trip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Trip>() {
                    @Override
                    public void call(Trip trip) {
                        TripsActivity.restart(UpdatedFullTripActivity.this, "Trip updated");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("NewFullTripActivity", "Service subscribe error");
                        TripsActivity.restart(UpdatedFullTripActivity.this, null);
                    }
                });
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, UpdatedFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
