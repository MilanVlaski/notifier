package com.akimi.notifier.api.outbound;

public interface NotificationDelegator {
    void send(Runnable delegate);
}
