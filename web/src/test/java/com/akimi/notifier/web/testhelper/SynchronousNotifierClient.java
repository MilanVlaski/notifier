package com.akimi.notifier.web.testhelper;

import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.values.Channel;
import com.akimi.notifier.api.values.Message;
import com.akimi.notifier.api.values.To;

public class SynchronousNotifierClient implements ForRequestingNotifications {



    public void requestNotification(Channel email, To param, Message message) {

    }

    public void setUp() {
    }

    public void tearDown() {
    }
}
