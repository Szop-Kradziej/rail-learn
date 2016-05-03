package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Offer;
import com.drabarz.karola.raillearn.model.Route;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.model.User;
import com.drabarz.karola.raillearn.trip.details.NewFullTripActivity;

public class NewTripOfferActivity extends AppCompatActivity {

    private static final String ROUTE = "route";
    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_trip_offer_activity);

        route = (Route) getIntent().getSerializableExtra(ROUTE);

        addConfirmOfferButtonListener();
    }

    private void addConfirmOfferButtonListener() {
        Button confirmOfferButton = (Button) findViewById(R.id.confirmOfferButton);
        confirmOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmOfferButtonClicked();
            }
        });
    }

    private void onConfirmOfferButtonClicked() {
        Trip trip = createTripFromInputData();
        startNewFullTripActivity(trip);
    }

    private Trip createTripFromInputData() {
        Offer offer = getOfferInputData();
        User user = TestData.getExampleUser();

        return new Trip(user, offer, route);
    }

    private Offer getOfferInputData() {
        String title = ((EditText) findViewById(R.id.inputTripTitleEditText)).getText().toString();
        String description = ((EditText) findViewById(R.id.inputTripDescriptionEditText)).getText().toString();

        return new Offer(title, description);
    }

    private void startNewFullTripActivity(Trip trip) {
        NewFullTripActivity.start(this, trip);
    }

    public static void start(Context context, Route route) {
        Intent intent = new Intent(context, NewTripOfferActivity.class);
        intent.putExtra(ROUTE, route);
        context.startActivity(intent);
    }
}
