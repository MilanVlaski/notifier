package com.akimi.notifier.api.outbound.defaults;

import com.akimi.notifier.api.outbound.*;
import com.akimi.notifier.api.values.*;

// can be made into a generic fake for SMS, etc
public class FakeEmailNotificationSender
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
