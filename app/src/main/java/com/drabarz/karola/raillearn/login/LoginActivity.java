package com.drabarz.karola.raillearn.login;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Token;
import com.drabarz.karola.raillearn.service.RailLearnLogInUserApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.trip.list.TripsActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginActivity extends ActionBarActivity {

    private static final String TOKEN = "token";
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isLoggedIn()) {
            TripsActivity.start(LoginActivity.this);
        } else {
            showLogInForm();
        }
    }

    private boolean isLoggedIn() {
        return PreferenceManager.getDefaultSharedPreferences(this).contains(TOKEN);
    }

    private void showLogInForm() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.login_activity);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                onFacebookLogInSuccess(loginResult.getAccessToken().getToken());
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

    private void onLogInSuccess(String token) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(TOKEN, token).commit();
        TripsActivity.start(LoginActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void onFacebookLogInSuccess(String facebookToken) {
        RailLearnLogInUserApi retrofitService = ServiceFactory.createRetrofitService(RailLearnLogInUserApi.class, getString(R.string.service_endpoint));
        retrofitService.logInUser(facebookToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Token>() {
                               @Override
                               public void call(Token token) {
                                   onLogInSuccess(token.getToken());
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   Log.e("LoginActivity", throwable.getMessage(), throwable);
                               }
                           }
                );
    }
}
