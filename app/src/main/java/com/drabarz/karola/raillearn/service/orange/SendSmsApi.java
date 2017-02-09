package com.drabarz.karola.raillearn.service.orange;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface SendSmsApi {
    @GET("/Messaging/v1/SMSOnnet?to=48666333222&apikey=qr1d7R3Ag3gop06s1bzRuySh7fxukfSA")
    Observable<SmsResponse> sendSms(@Query("msg") String message);
}
