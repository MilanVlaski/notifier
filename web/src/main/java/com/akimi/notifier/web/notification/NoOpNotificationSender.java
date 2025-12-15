package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.outbound.*;

import com.akimi.notifier.api.values.*;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
@Profile("!prod")
public class NoOpNotificationSender implements ForSendingNotifications {
    @Override
    public void sendNotification(To to, Message message) {

    }

    @Override
    public Channel channel() {
        return Channel.EMAIL;
    }
}
