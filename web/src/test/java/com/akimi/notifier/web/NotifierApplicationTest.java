package com.akimi.notifier.web;

import com.akimi.notifier.api.values.*;
import com.akimi.notifier.web.testhelper.*;

import org.junit.jupiter.api.*;

public class NotifierApplicationTest {

    final SynchronousNotifierClient app = new SynchronousNotifierClient();
    final To to = new To("milan@gmail.com");
    final MailhogServer recipient = new MailhogServer();

    @BeforeEach
    public void setUp() {
        app.setUp();
        recipient.setUp();
    }

    @Test
    @Disabled
    public void Sends_email_to_mailhog_server() {
        var message = new Message("Hello!");
        app.requestNotification(Channel.EMAIL, to, message);
        recipient.hasReceivedMessage(message);
    }

    @AfterEach
    public void tearDown() {
        app.tearDown();
        recipient.tearDown();
    }
}
