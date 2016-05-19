package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.RailLearnDeleteTripApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.create.EditTripRouteActivity;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SelectedMyFullTripActivity extends FullTripActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setVisibility(Button.VISIBLE);
        addDeleteButtonListener();
    }

    private void addDeleteButtonListener() {
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteButtonClicked();
            }
        });
    }

    private void onDeleteButtonClicked() {
        RailLearnDeleteTripApi retrofitService = ServiceFactory.createRetrofitService(RailLearnDeleteTripApi.class, getString(R.string.service_endpoint));
        retrofitService.deleteTrip(trip.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String id) {
                        TripsActivity.restart(SelectedMyFullTripActivity.this);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("SelectedMyTripActivity", "Service subscribe error");
                    }
                });
    }

    @Override
    protected String getConfirmButtonText() {
        return getString(R.string.edit);
    }

    @Override
    protected void onConfirmButtonClicked() {
        EditTripRouteActivity.start(this, trip);
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, SelectedMyFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
