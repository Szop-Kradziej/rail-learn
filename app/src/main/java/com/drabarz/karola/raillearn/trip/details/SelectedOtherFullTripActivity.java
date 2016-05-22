package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.RailLearnJoinTripApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SelectedOtherFullTripActivity extends FullTripActivity {

    @Override
    protected String getConfirmButtonText() {
        return getString(R.string.join);
    }

    @Override
    protected void onConfirmButtonClicked() {
        String myId = PreferenceManager.getDefaultSharedPreferences(this).getString("user_id", null);

        RailLearnJoinTripApi retrofitService = ServiceFactory.createRetrofitService(RailLearnJoinTripApi.class, getString(R.string.service_endpoint));
        retrofitService.joinTrip(trip.getId(), myId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Trip>() {
                    @Override
                    public void call(Trip trip) {
                        TripsActivity.restart(SelectedOtherFullTripActivity.this);
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
        Intent intent = new Intent(context, SelectedOtherFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
