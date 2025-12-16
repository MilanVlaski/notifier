package com.akimi.notifier.api;

import com.akimi.notifier.api.inbound.*;
import com.akimi.notifier.api.values.*;

public class Notifier implements ForRequestingNotifications {


    private final Broker broker;

    public Notifier(Broker broker) {
        this.broker = broker;
    }

    @Override
    public void requestNotification(Notification notification) {
        broker.breakMessage(notification);
    }
}
