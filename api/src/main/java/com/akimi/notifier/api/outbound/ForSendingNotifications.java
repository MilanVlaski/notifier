package com.akimi.notifier.api.outbound;

import com.akimi.notifier.api.values.*;

public interface ForSendingNotifications {
    void sendNotification(From from, To to, Message message);
    Channel channel();
}
