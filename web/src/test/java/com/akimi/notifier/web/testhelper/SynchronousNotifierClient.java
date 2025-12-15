package com.akimi.notifier.web.testhelper;

import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.values.Channel;
import com.akimi.notifier.api.values.Message;
import com.akimi.notifier.api.values.To;
import com.akimi.notifier.web.NotifierApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class SynchronousNotifierClient implements ForRequestingNotifications {

    private ConfigurableApplicationContext context;
    private ForRequestingNotifications notifier;

    @Override
    public void requestNotification(Channel channel, To to, Message message) {
        notifier.requestNotification(channel, to, message);
    }

    public void setUp(String host, int emailPort) {
        if (context != null) {
            return;
        }

        context = new SpringApplicationBuilder(NotifierApplication.class)
                .run(
                        "--spring.profiles.active=prod",
                        "--spring.mail.host=" + host,
                        "--spring.mail.port=" + emailPort
                );

        notifier = context.getBean(ForRequestingNotifications.class);
    }

    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

}
