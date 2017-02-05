package com.drabarz.karola.raillearn.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.service.RailLearnJoinTripApi;
import com.drabarz.karola.raillearn.service.ServiceFactory;
import com.drabarz.karola.raillearn.service.orange.SmsResponse;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SmsActiity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sms_activity);

        findViewById(R.id.sendSmsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsSend();
            }
        });
    }

    private void smsSend() {
        RailLearnJoinTripApi retrofitSmsService = ServiceFactory.createRetrofitService(RailLearnJoinTripApi.class, getString(R.string.sms_endpoint));
        retrofitSmsService.sendSms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SmsResponse>() {
                    @Override
                    public void call(SmsResponse smsResponse) {
                        Log.i("Sms notification send", smsResponse.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("SelectFullTripActivity", "Sms send error", throwable);
                    }
                });
    }
}
