package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.outbound.*;
import com.akimi.notifier.api.values.*;
import com.akimi.notifier.web.notification.senders.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;

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
