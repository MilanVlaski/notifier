package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.Notifier;
import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.outbound.NotificationDelegator;
import com.akimi.notifier.api.outbound.NotificationSenderFactory;
import com.akimi.notifier.api.outbound.defaults.BasicNotificationDelegate;
import com.akimi.notifier.api.outbound.defaults.FakeEmailNotificationSender;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class NotifierConfig {

    @Bean
    public ForRequestingNotifications notifier(
            NotificationDelegator notifierDelegate,
            NotificationSenderFactory notificationSenders
    ) {
        return new Notifier(notifierDelegate, notificationSenders);
    }


    @Bean
    public NotificationDelegator notifierDelegate() {
        return new BasicNotificationDelegate();
    }

    @Bean
    @Profile("!prod")
    public NotificationSenderFactory defaultNotificationSender() {
        return (x) -> new FakeEmailNotificationSender();
    }

    @Bean
    @Profile("insecure")
    public SecurityFilterChain security(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
