package com.akimi.notifier.web.notification.senders;

import com.akimi.notifier.api.outbound.EmailNotificationValidator;
import com.akimi.notifier.api.outbound.ForSendingNotifications;
import com.akimi.notifier.api.values.EmailNotification;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailNotificationSender implements ForSendingNotifications {

    private final JavaMailSender mailSender;
    private final EmailNotification notification;

    public EmailNotificationSender(JavaMailSender mailSender,
                                   EmailNotification emailNotification) {
        this.mailSender = mailSender;
        this.notification = emailNotification;
    }

    @Override
    public void sendNotification() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(notification.from().email());
        mail.setTo(notification.to().email());
        mail.setSubject(notification.message().subject());
        mail.setText(notification.message().body());
        mailSender.send(mail);
    }

    @Override
    public void validate() {
        new EmailNotificationValidator(notification).validate();
    }

}
