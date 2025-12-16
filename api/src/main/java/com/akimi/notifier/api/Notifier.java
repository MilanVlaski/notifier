package com.akimi.notifier.api;

import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.outbound.NotificationDelegator;
import com.akimi.notifier.api.outbound.NotificationSenderFactory;
import com.akimi.notifier.api.values.NotificationRequest;


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
        var sender = senderFactory.create(request);
        sender.validate();
        delegate.send(() -> sender.sendNotification());
    }

}
