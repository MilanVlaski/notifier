package com.akimi.notifier.api;

import com.akimi.notifier.api.outbound.defaults.*;
import com.akimi.notifier.api.values.*;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {

    final FakeEmailNotificationSender fakeEmailNotificationSender =
           new FakeEmailNotificationSender();
    final Notifier notifier = new Notifier(new BasicNotificationDelegate(),
            List.of(fakeEmailNotificationSender));

    @Test
    public void Send_notification() {
        var to = new To("franklin@gmail.com");
        var message = new Message("Hello!", "Msg");
        var from = new From("me@email.com");
        notifier.requestNotification(new EmailNotification(from, to, message));

        assertTrue(fakeEmailNotificationSender.hasSentNotification);
    }
}
