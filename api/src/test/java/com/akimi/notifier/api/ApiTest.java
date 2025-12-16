package com.akimi.notifier.api;

import com.akimi.notifier.api.outbound.defaults.*;
import com.akimi.notifier.api.values.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {

    final FakeEmailNotificationSender fakeEmailNotificationSender =
            new FakeEmailNotificationSender();
    final Notifier notifier = new Notifier(new BasicNotificationDelegate(),
            (x) -> fakeEmailNotificationSender
    );

    @Test
    public void Send_notification() {
        notifier.requestNotification(new NotificationRequest(Channel.EMAIL,
                "me@email.com", "franklin@gmail.com",
                new Message("Hello!", "Msg")));

        assertTrue(fakeEmailNotificationSender.hasSentNotification);
    }
}
