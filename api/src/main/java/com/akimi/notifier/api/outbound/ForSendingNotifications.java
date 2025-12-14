package com.akimi.notifier.api.outbound;

import com.akimi.notifier.api.values.Channel;
import com.akimi.notifier.api.values.Message;
import com.akimi.notifier.api.values.To;

public interface ForSendingNotifications {
    void sendNotification(To to, Message message);
    Channel channel();
}
