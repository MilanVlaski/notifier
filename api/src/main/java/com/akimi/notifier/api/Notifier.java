package com.akimi.notifier.api;

import com.akimi.notifier.api.inbound.*;
import com.akimi.notifier.api.outbound.*;
import com.akimi.notifier.api.values.*;

public class Notifier implements ForRequestingNotifications {

    private final ForSendingNotifications[] notificationSenders;
    private final NotificationDelegator delegate;

    public Notifier(NotificationDelegator delegate,
                    ForSendingNotifications... notificationSenders) {
        this.delegate = delegate;
        this.notificationSenders = notificationSenders;
    }


    @Override
    public void requestNotification(Notification notification) {
        var sender = findSenderForChannel(notification);
        delegate.send(() -> sender.sendNotification(notification));
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
