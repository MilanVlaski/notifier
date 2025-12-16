package com.akimi.notifier.api;

import com.akimi.notifier.api.inbound.*;
import com.akimi.notifier.api.outbound.*;
import com.akimi.notifier.api.values.*;


public class Notifier implements ForRequestingNotifications {

    private final NotificationSenderFactory senderFactory;
    private final NotificationDelegator delegate;

    public Notifier(NotificationDelegator delegate,
                    NotificationSenderFactory senderFactory) {
        this.delegate = delegate;
        this.senderFactory = senderFactory;
    }


    @Override
    public void requestNotification(NotificationRequest request) {
        // Notification request comes here.
        // Sender is instantiated with correct notification instance.
        // var sender = senderSupplier.supply(notificationRequest);
        // sender.validate();
        // delegate.send(() -> sender.send());
        var sender = senderFactory.create(request);
//        var sender = findSenderForChannel(request);
        delegate.send(() -> sender.sendNotification());
    }

//    private ForSendingNotifications findSenderForChannel(Notification notification) {
//        var sender = senderFactory.get(notification.getClass());
//        if (sender == null) {
//            throw new RuntimeException("Sender not found for channel: "
//                + notification.getClass().getSimpleName());
//        } else {
//            return sender;
//        }
//    }
}
