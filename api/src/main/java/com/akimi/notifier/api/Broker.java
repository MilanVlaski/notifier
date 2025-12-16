package com.akimi.notifier.api;

import com.akimi.notifier.api.outbound.*;
import com.akimi.notifier.api.values.*;

public class Broker {
    private final ForSendingNotifications[] notificationSenders;

    public Broker(ForSendingNotifications... notificationSenders) {
        this.notificationSenders = notificationSenders;
    }


    public void breakMessage(Notification notification) {
        findSenderForChannel(notification)
                .sendNotification(notification);
    }

    private ForSendingNotifications findSenderForChannel(Notification notification) {
        for (ForSendingNotifications sender : notificationSenders) {
            if (sender.supportedType().equals(notification.getClass())) {
                return sender;
            }
        }
            throw new RuntimeException("Sender not found for channel: "
                    + notification.getClass().getSimpleName());
    }
}
