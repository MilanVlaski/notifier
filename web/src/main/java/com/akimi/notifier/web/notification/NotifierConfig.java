package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.Broker;
import com.akimi.notifier.api.Notifier;
import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.outbound.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotifierConfig {

    @Bean
    public ForRequestingNotifications notifier(
            ForSendingNotifications notificationSender
    ) {
        return new Notifier(new Broker(notificationSender));
    }
}
