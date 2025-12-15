package com.akimi.notifier.web.notification;

import com.akimi.notifier.api.Broker;
import com.akimi.notifier.api.Notifier;
import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.outbound.*;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.web.*;

@Configuration
public class NotifierConfig {

    @Bean
    public ForRequestingNotifications notifier(
            ForSendingNotifications notificationSender
    ) {
        return new Notifier(new Broker(notificationSender));
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
