package com.akimi.notifier.api;

import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.mockito.Mockito.verify;

public class ApiTest {

    final ForSendingNotifications fakeNotificationSender =
            Mockito.mock(ForSendingNotifications.class);
    final Notifier notifier = new Notifier(fakeNotificationSender);

    @Test
    public void Send_notification() {
        notifier.requestNotification();
        verify(fakeNotificationSender).sendNotification();
    }
}
