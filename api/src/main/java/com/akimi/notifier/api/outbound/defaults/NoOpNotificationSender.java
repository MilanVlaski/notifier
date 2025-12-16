package com.akimi.notifier.api.outbound.defaults;

import com.akimi.notifier.api.outbound.*;
import com.akimi.notifier.api.values.*;

public class NoOpNotificationSender
        implements ForSendingNotifications<EmailNotification> {

    public boolean hasSentNotification = false;

    @Override
    public Class<EmailNotification> supportedType() {
        return EmailNotification.class;
    }

    @Override
    public void sendNotification(EmailNotification notification) {
        hasSentNotification = true;
    }

}
