package com.drabarz.karola.raillearn.trip.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setVisibility(Button.GONE);

        setCancelButton();
    }

    private void setCancelButton() {
        Button cancelButton = (Button) findViewById(R.id.deleteButton);
        cancelButton.setVisibility(Button.VISIBLE);
        cancelButton.setText(getConfirmButtonText());
        addCancelButtonListener(cancelButton);
    }

    @Override
    protected String getConfirmButtonText() {
        return getString(R.string.cancel);
    }

    private void addCancelButtonListener(Button cancelButton) {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmButtonClicked();
            }
        });
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
                        TripsActivity.restart(SelectedMyRequestedFullTripActivity.this, "Trip canceled");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("SelectedMyRequestedTrip", "Service subscribe error");
                        TripsActivity.restart(SelectedMyRequestedFullTripActivity.this, null);
                    }
                });
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, SelectedMyRequestedFullTripActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
