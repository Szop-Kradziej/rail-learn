package com.drabarz.karola.raillearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.drabarz.karola.raillearn.adapter.TripsGroupAdapter;

public class TripsActivity extends AppCompatActivity{

    private TripsGroupAdapter tripsGroupAdapter = new TripsGroupAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);

        setAdapter();
    }

    private void setAdapter() {
        ListView tripsGroupView = (ListView) findViewById(R.id.tripsGroupView);
        tripsGroupView.setAdapter(tripsGroupAdapter);
    }
}
