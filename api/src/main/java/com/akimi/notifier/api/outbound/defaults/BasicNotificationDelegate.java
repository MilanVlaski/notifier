package com.akimi.notifier.api.outbound.defaults;

import com.akimi.notifier.api.outbound.*;

/**
 * Synchronous implementation that should be available by default.
 */
public class BasicNotificationDelegate implements NotificationDelegator {
    @Override
    public void send(NotificationDelegate delegate) {
        delegate.send();
    }
}
