package com.drabarz.karola.raillearn.trip.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.service.RailLearnService;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.create.NewTripRouteActivity;
import com.drabarz.karola.raillearn.trip.details.SelectedMyFullTripActivity;
import com.drabarz.karola.raillearn.trip.details.SelectedOtherFullTripActivity;
import com.drabarz.karola.raillearn.trip.details.SelectedMyRequestedFullTripActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TripsActivity extends AppCompatActivity {

    private final static String TEXT_TO_SHOW = "text_to_show";
    private TripsGroupAdapter tripsGroupAdapter = new TripsGroupAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.tripActivityToolbar));
        getSupportActionBar().setTitle(getString(R.string.trips));
        showText(getIntent().getStringExtra(TEXT_TO_SHOW));

        setAdapter();
        setService();
        setNewTripButtonListener();
    }

    private void showText(String text) {
        if(text != null) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.tripsActivityLayout);
            Snackbar snackbar = Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    private void setAdapter() {
        ListView tripsGroupView = (ListView) findViewById(R.id.tripsGroupView);
        tripsGroupView.setAdapter(tripsGroupAdapter);
    }

    private void setService() {
        String myId = PreferenceManager.getDefaultSharedPreferences(this).getString("user_id", null);

        RailLearnService service = ServiceFactory.createRetrofitService(RailLearnService.class, getString(R.string.service_endpoint));
        service.getTrip(myId)
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

    public void startSelectedMyFullTripActivity(Trip trip) {
        SelectedMyFullTripActivity.start(this, trip);
    }

    public void startSelectedMyRequestedFullTripActivity(Trip trip) {
        SelectedMyRequestedFullTripActivity.start(this, trip);
    }

    public void startSelectedOtherFullTripActivity(Trip trip) {
        SelectedOtherFullTripActivity.start(this, trip);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.trips_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.allTripsMenuItem:
                tripsGroupAdapter.showAll();
                return true;
            case R.id.myOffersMenuItem:
                tripsGroupAdapter.showMyOffers();
                return true;
            case R.id.myRequestsMenuItem:
                tripsGroupAdapter.showMyRequests();
                return true;
            case R.id.otherOffersMenuItem:
                tripsGroupAdapter.showOtherOffers();
                return true;
            default:
                return false;
        }
    }

    private void startNewTripActivity() {
        NewTripRouteActivity.start(this);
    }

    public static void start(Context context, String text) {
        Intent intent = new Intent(context, TripsActivity.class);
        intent.putExtra(TEXT_TO_SHOW, text);
        context.startActivity(intent);
    }

    public static void restart(Context context, String text) {
        Intent intent = new Intent(context, TripsActivity.class);
        intent.putExtra(TEXT_TO_SHOW, text);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
