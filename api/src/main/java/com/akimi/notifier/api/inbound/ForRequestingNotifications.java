package com.akimi.notifier.api.inbound;

import com.akimi.notifier.api.values.Channel;
import com.akimi.notifier.api.values.Message;
import com.akimi.notifier.api.values.To;

public interface ForRequestingNotifications {
    void requestNotification(Channel email, To param, Message message);
}
