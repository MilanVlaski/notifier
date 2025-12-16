package com.akimi.notifier.api.outbound;

import com.akimi.notifier.api.values.*;

public interface ForSendingNotifications<T extends Notification> {
    // Refactor to use sealed interface called Notification
    Class<T> supportedType();
    void sendNotification(T notification);
}
