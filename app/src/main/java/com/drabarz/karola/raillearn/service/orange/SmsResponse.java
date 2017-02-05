package com.drabarz.karola.raillearn.service.orange;

import java.io.Serializable;

public class SmsResponse implements Serializable {

    private final String result;
    private final String id;
    private final String deliveryMessage;

    public SmsResponse(String result, String id, String deliveryMessage) {
        this.result = result;
        this.id = id;
        this.deliveryMessage = deliveryMessage;
    }

    public String getResult() {
        return result;
    }

    public String getId() {
        return id;
    }

    public String getDeliveryMessage() {
        return deliveryMessage;
    }
}
