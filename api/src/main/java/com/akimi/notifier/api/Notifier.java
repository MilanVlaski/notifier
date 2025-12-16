package com.akimi.notifier.api;

import com.akimi.notifier.api.inbound.*;
import com.akimi.notifier.api.outbound.*;
import com.akimi.notifier.api.values.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Notifier implements ForRequestingNotifications {

    private final Map<
        Class<? extends Notification>, ForSendingNotifications> notificationSenders;
    private final NotificationDelegator delegate;

    public Notifier(NotificationDelegator delegate,
                    List<ForSendingNotifications> notificationSenders) {
        this.delegate = delegate;
        this.notificationSenders = notificationSenders.stream()
            .collect(
                toMap(sender -> sender.supportedType(),
                    sender -> sender)
            );
    }


    @Override
    public void requestNotification(Notification notification) {
        var sender = findSenderForChannel(notification);
        delegate.send(() -> sender.sendNotification(notification));
    }

    private ForSendingNotifications findSenderForChannel(Notification notification) {
        var sender = notificationSenders.get(notification.getClass());
        if (sender == null) {
            throw new RuntimeException("Sender not found for channel: "
                + notification.getClass().getSimpleName());
        } else {
            return sender;
        }
    }
}
