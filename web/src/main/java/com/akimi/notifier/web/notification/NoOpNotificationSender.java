package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.outbound.*;

import com.akimi.notifier.api.values.*;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
@Profile("!prod")
public class NoOpNotificationSender implements ForSendingNotifications {
    @Override
    public Class supportedType() {
        return EmailNotification.class;
    }

    @Override
    public void sendNotification(Notification notification) {

    }

}
