package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.*;
import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.outbound.*;

import com.akimi.notifier.api.outbound.defaults.*;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.web.*;

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
