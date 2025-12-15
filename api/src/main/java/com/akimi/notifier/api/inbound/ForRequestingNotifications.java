package com.akimi.notifier.api.inbound;

import com.akimi.notifier.api.values.*;

public interface ForRequestingNotifications {
    void requestNotification(Channel email, From from, To param, Message message);
}
