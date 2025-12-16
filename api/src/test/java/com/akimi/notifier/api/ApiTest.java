package com.akimi.notifier.api;

import com.akimi.notifier.api.outbound.*;

import com.akimi.notifier.api.outbound.defaults.*;
import com.akimi.notifier.api.values.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApiTest {

    // TODO create the fake as a reusable component, and use it as a default in the real app!
    final NoOpNotificationSender fakeEmailNotificationSender =
           new NoOpNotificationSender();
    final Notifier notifier = new Notifier(new BasicNotificationDelegate(), List.of(fakeEmailNotificationSender));

    @Test
    public void Send_notification() {
        var to = new To("franklin@gmail.com");
        var message = new Message("Hello!", "Msg");
        var from = new From("me@email.com");
        notifier.requestNotification(new EmailNotification(from, to, message));

        assertTrue(fakeEmailNotificationSender.hasSentNotification);
    }
}
