package com.akimi.notifier.web;

import com.akimi.notifier.api.values.*;
import com.akimi.notifier.web.testhelper.*;

import org.junit.jupiter.api.*;

public class NotifierApplicationTest {

    final SynchronousNotifierClient app = new SynchronousNotifierClient();
    private static final int HTTP_TEST_PORT = 8025;
    private static final int EMAIL_PORT = 1025;
    final MailhogServer recipient = new MailhogServer();

    @BeforeEach
    public void setUp() {
        recipient.setUp(EMAIL_PORT, HTTP_TEST_PORT);
        app.setUp(recipient.smtpHost(), recipient.smtpPort(), "8080");
    }

    @Test
    public void Sends_email_to_mailhog_server() {
        var message = new Message("Hello!", "bla");
        app.sendEmail(new NotificationRequest(
                Channel.EMAIL, "me@gmail.com", "milan@gmail.com", message));
        recipient.hasReceivedMessage(message);
    }

    @AfterEach
    public void tearDown() {
        app.tearDown();
        recipient.tearDown();
    }
}
