package com.drabarz.karola.raillearn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        bindTripsButtonAction();
    }

    private void bindTripsButtonAction() {
        Button tripsButton = (Button) findViewById(R.id.tripsButton);
        tripsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTripsButtonClick();
            }
        });
    }

    private void onTripsButtonClick() {
        Intent intent = new Intent(this, TripsActivity.class);
        startActivity(intent);
    }
}
