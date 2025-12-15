package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.outbound.ForSendingNotifications;
import com.akimi.notifier.api.values.Channel;
import com.akimi.notifier.api.values.Message;
import com.akimi.notifier.api.values.To;

import org.springframework.context.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class EmailNotificationSender implements ForSendingNotifications {

    private final JavaMailSender mailSender;

    public EmailNotificationSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendNotification(To to, Message message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("no-reply@notifier.local");
        mail.setTo(to.email());
        mail.setSubject(message.subject());
        mail.setText(message.body());
        mailSender.send(mail);
    }

    @Override
    public Channel channel() {
        return Channel.EMAIL;
    }
}
