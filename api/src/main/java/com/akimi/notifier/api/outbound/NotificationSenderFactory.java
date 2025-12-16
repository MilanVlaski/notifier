package com.akimi.notifier.api.outbound;

import com.akimi.notifier.api.values.*;

public interface NotificationSenderFactory {
    ForSendingNotifications create(NotificationRequest request);
}
