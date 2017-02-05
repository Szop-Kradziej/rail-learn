package com.drabarz.karola.raillearn.login;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.LoginData;
import com.drabarz.karola.raillearn.service.RailLearnLogInUserApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginActivity extends ActionBarActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TOKEN = "token";
    private static final String USER_ID = "user_id";
    private static final int RC_SIGN_IN = 1276;
    private CallbackManager callbackManager;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isLoggedIn()) {
            TripsActivity.start(LoginActivity.this, null);
        } else {
            showLogInForm();
        }
    }

    private boolean isLoggedIn() {
        return PreferenceManager.getDefaultSharedPreferences(this).contains(TOKEN);
    }

    private void showLogInForm() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_activity);
        showFacebookLogInForm();
        showGoogleLogInForm();
    }

    private void showFacebookLogInForm() {
        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                onLogInSuccess(loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                // Do nothing
            }

            @Override
            public void onError(FacebookException e) {
                Log.e("LoginActivity", "Facebook login error: " + e, e);
            }
        });
    }

    private void showGoogleLogInForm() {
        GoogleSignInOptions googleSingInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSingInOptions)
                .build();

        findViewById(R.id.google_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void onLogInSuccess(LoginData data) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(TOKEN, data.getToken()).commit();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(USER_ID, data.getId()).commit();
        TripsActivity.start(LoginActivity.this, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            return;
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(GoogleSignInResult result) {
      //  if (result.isSuccess()) {
      //      GoogleSignInAccount account = result.getSignInAccount();
            onLogInSuccess(getRandomToken());//account.getIdToken());
      //  } else {
      //      Log.e("LoginActivity", "Google login error: " + result.getStatus().getStatusMessage());
      //  }
    }

    private void onLogInSuccess(String token) {
        RailLearnLogInUserApi retrofitService = ServiceFactory.createRetrofitService(RailLearnLogInUserApi.class, getString(R.string.service_endpoint));
        retrofitService.logInUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginData>() {
                               @Override
                               public void call(LoginData data) {
                                   onLogInSuccess(data);
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   Log.e("LoginActivity", throwable.getMessage(), throwable);
                               }
                           }
                );
    }

    private String getRandomToken() {
        return String.valueOf(new Random().nextLong());
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("LoginActivity", "On connection failed: " + connectionResult.toString());
    }
}
