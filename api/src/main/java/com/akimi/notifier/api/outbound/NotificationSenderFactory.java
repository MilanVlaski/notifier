package com.akimi.notifier.api.outbound;

import com.akimi.notifier.api.values.NotificationRequest;

public interface NotificationSenderFactory {
    ForSendingNotifications create(NotificationRequest request);
}
