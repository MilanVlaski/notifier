package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.outbound.ForSendingNotifications;
import com.akimi.notifier.api.values.*;

import org.springframework.context.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class EmailNotificationSender
        implements ForSendingNotifications<EmailNotification> {

    private final JavaMailSender mailSender;

    public EmailNotificationSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Class<EmailNotification> supportedType() {
        return EmailNotification.class;
    }

    @Override
    public void sendNotification(EmailNotification notification) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(notification.from().email());
        mail.setTo(notification.to().email());
        mail.setSubject(notification.message().subject());
        mail.setText(notification.message().body());
        mailSender.send(mail);
    }

}
