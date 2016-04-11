package com.drabarz.karola.raillearn.trip.list;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.model.test.TestData;
import com.drabarz.karola.raillearn.service.RailLearnService;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.details.FullTripActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TripsActivity extends AppCompatActivity {

    private TripsGroupAdapter tripsGroupAdapter = new TripsGroupAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);

        setAdapter();
        setService();
    }

    private void setAdapter() {
        TestData.fillTripsListWithTestData(tripsGroupAdapter, 4);

        tripsGroupAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                onDataSetChange();
            }
        });

        ListView tripsGroupView = (ListView) findViewById(R.id.tripsGroupView);
        tripsGroupView.setAdapter(tripsGroupAdapter);
    }

    private void onDataSetChange() {
        Trip selectedTrip = tripsGroupAdapter.getSelectedTripItem().getTrip();
        startFullTripActivity(selectedTrip);
    }

    private void setService() {
        RailLearnService service = ServiceFactory.createRetrofitService(RailLearnService.class, RailLearnService.SERVICE_ENDPOINT);

        service.getTrip()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Trip>>() {
                    @Override
                    public void call(List<Trip> trips) {
                        for (Trip trip : trips) {
                            tripsGroupAdapter.addTripItem(new TripItem(trip));
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("TripsActivity", "Service subscribe error");
                    }
                });
    }

    private void startFullTripActivity(Trip trip) {
        FullTripActivity.start(this, trip);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, TripsActivity.class);
        context.startActivity(intent);
    }
}
