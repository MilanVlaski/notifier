package com.akimi.notifier.api.outbound;


public interface ForSendingNotifications extends ForValidatingNotifications {
    void sendNotification();
}
