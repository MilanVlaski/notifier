package com.akimi.notifier.api.inbound;

import com.akimi.notifier.api.values.NotificationRequest;

public interface ForRequestingNotifications {
    void requestNotification(NotificationRequest request);
}
