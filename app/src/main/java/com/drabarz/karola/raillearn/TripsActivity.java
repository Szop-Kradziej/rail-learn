package com.drabarz.karola.raillearn;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.drabarz.karola.raillearn.adapter.TripsGroupAdapter;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.test.TestData;

public class TripsActivity extends AppCompatActivity {

    private TripsGroupAdapter tripsGroupAdapter = new TripsGroupAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);

        setAdapter();
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

    private void startFullTripActivity(Trip trip) {
        FullTripActivity.start(this, trip);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, TripsActivity.class);
        context.startActivity(intent);
    }
}
