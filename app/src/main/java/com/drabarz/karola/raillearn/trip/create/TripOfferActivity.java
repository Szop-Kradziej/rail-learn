package com.drabarz.karola.raillearn.trip.create;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Offer;
import com.drabarz.karola.raillearn.model.Route;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.model.User;

public abstract class TripOfferActivity extends AppCompatActivity {
    protected static final String ROUTE = "route";
    protected static final String DESCRIPTION = "description";
    protected Route route;

    protected abstract void onConfirmOfferButtonClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_trip_offer_activity);

        route = (Route) getIntent().getSerializableExtra(ROUTE);

        addConfirmOfferButtonListener();
    }

    protected void addConfirmOfferButtonListener() {
        Button confirmOfferButton = (Button) findViewById(R.id.confirmOfferButton);
        confirmOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmOfferButtonClicked();
            }
        });
    }

    protected Trip createTripFromInputData() {
        Offer offer = getOfferInputData();
        String userId = getUserId();
        Log.i("NewTripOfferAct", "User id: " + userId);
        User user = new User("Karolina", userId, "http://i.imgur.com/jwsfAfh.jpg");

        return new Trip(user, offer, route);
    }

    private Offer getOfferInputData() {
        String title = ((EditText) findViewById(R.id.inputTripTitleEditText)).getText().toString();
        String description = ((EditText) findViewById(R.id.inputTripDescriptionEditText)).getText().toString();

        return new Offer(title, description);
    }

    private String getUserId() {
        return PreferenceManager.getDefaultSharedPreferences(this).getString("user_id", null);
    }

    protected void saveDescription(String description) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(DESCRIPTION,description).commit();
    }
}
