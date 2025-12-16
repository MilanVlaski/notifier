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
            ForSendingNotifications notificationSender
    ) {
        return new Notifier(notifierDelegate, notificationSender);
    }

    @Bean
    @Profile("insecure")
    public SecurityFilterChain security(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public NotificationDelegator notifierDelegate() {
        return new BasicNotificationDelegate();
    }

    @Bean
    @Profile("!prod")
    public ForSendingNotifications defaultNotificationSender() {
        return new NoOpNotificationSender();
    }
}
