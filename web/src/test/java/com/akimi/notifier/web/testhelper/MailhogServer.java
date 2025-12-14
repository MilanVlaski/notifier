package com.akimi.notifier.web.testhelper;

import com.akimi.notifier.api.values.*;

public class MailhogServer {
    public void setUp() {
    }

    public void hasReceivedMessage(Message message) {
        throw new RuntimeException("Message has not been received.");
    }

    public void tearDown() {
    }
}
