package com.drabarz.karola.raillearn.trip.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.RailLearnService;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.create.NewTripRouteActivity;
import com.drabarz.karola.raillearn.trip.details.SelectedMyFullTripActivity;
import com.drabarz.karola.raillearn.trip.details.SelectedOtherFullTripActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TripsActivity extends AppCompatActivity {

    private TripsGroupAdapter tripsGroupAdapter = new TripsGroupAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.tripActivityToolbar));
        getSupportActionBar().setTitle(getString(R.string.app_name));

        setAdapter();
        setService();
        setNewTripButtonListener();
    }

    private void setAdapter() {
        ListView tripsGroupView = (ListView) findViewById(R.id.tripsGroupView);
        tripsGroupView.setAdapter(tripsGroupAdapter);
    }

    private void setService() {
        RailLearnService service = ServiceFactory.createRetrofitService(RailLearnService.class, getString(R.string.service_endpoint));

        service.getTrip()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Trip>>() {
                    @Override
                    public void call(List<Trip> trips) {
                        tripsGroupAdapter.replaceTripItems(trips);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("TripsActivity", "Service subscribe error");
                    }
                });
    }

    public void startSelectedOtherFullTripActivity(Trip trip) {
        SelectedOtherFullTripActivity.start(this, trip);
    }

    public void startSelectedMyFullTripActivity(Trip trip) {
        SelectedMyFullTripActivity.start(this,trip);
    }

    private void setNewTripButtonListener() {
        FloatingActionButton newTripButton = (FloatingActionButton) findViewById(R.id.newTripButton);
        newTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewTripActivity();
            }
        });
    }

    private void startNewTripActivity() {
        NewTripRouteActivity.start(this);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, TripsActivity.class);
        context.startActivity(intent);
    }

    public static void restart(Context context) {
        Intent intent = new Intent(context, TripsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }


}
