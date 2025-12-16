package com.akimi.notifier.api.outbound;

import com.akimi.notifier.api.values.EmailNotification;

public class EmailNotificationValidator implements ForValidatingNotifications {

    private final EmailNotification notification;

    public EmailNotificationValidator(EmailNotification notification) {
        this.notification = notification;
    }

    @Override
    public void validate() {
        // TODO
    }
}
