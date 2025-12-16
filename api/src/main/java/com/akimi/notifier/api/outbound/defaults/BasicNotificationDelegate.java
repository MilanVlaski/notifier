package com.akimi.notifier.api.outbound.defaults;

import com.akimi.notifier.api.outbound.NotificationDelegator;

/**
 * Synchronous implementation that should be available by default.
 */
public class BasicNotificationDelegate implements NotificationDelegator {
    @Override
    public void send(Runnable delegate) {
        delegate.run();
    }
}
