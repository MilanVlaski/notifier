package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.outbound.ForSendingNotifications;
import com.akimi.notifier.api.outbound.NotificationSenderFactory;
import com.akimi.notifier.api.values.*;
import com.akimi.notifier.web.notification.senders.EmailNotificationSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class NotificationSenderFactorySpring implements NotificationSenderFactory {

    // TODO?
    @Autowired
    public JavaMailSender mailSender;

    @Override
    public ForSendingNotifications create(NotificationRequest request) {
        return switch (request.channel()) {
            case EMAIL -> new EmailNotificationSender(mailSender,
                    new EmailNotification(
                            new From(request.from()), new To(request.to()),
                            request.message()
                    ));
        };
    }
}
