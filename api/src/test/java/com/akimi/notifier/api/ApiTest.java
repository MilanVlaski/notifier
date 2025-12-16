package com.akimi.notifier.api;

import com.akimi.notifier.api.outbound.*;

import com.akimi.notifier.api.values.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApiTest {

    final ForSendingNotifications fakeNotificationSender =
            Mockito.mock(ForSendingNotifications.class);
    final Broker broker = new Broker(fakeNotificationSender);
    final Notifier notifier = new Notifier(broker);

    @Test
    public void Send_notification() {
        when(fakeNotificationSender.supportedType()).thenReturn(EmailNotification.class);

        var to = new To("franklin@gmail.com");
        var message = new Message("Hello!", "Msg");
        var from = new From("me@email.com");
        notifier.requestNotification(new EmailNotification(from, to, message));

        verify(fakeNotificationSender).sendNotification(new EmailNotification(from, to, message));
    }
}
