package com.akimi.notifier.web.testhelper;

import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.values.Channel;
import com.akimi.notifier.api.values.Message;
import com.akimi.notifier.api.values.To;
import com.akimi.notifier.web.NotifierApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class SynchronousNotifierClient implements ForRequestingNotifications {

    private ConfigurableApplicationContext context;
    private ForRequestingNotifications notifier;

    @Override
    public void requestNotification(Channel channel, To to, Message message) {
        notifier.requestNotification(channel, to, message);
    }

    public void setUp() {
        if (context != null) {
            return;
        }

        String smtpHost = System.getProperty("notifier.smtp.host", "localhost");
        String smtpPort = System.getProperty("notifier.smtp.port", "1025");

        // TODO nonsense?
        Map<String, Object> props = new HashMap<>();
        props.put("spring.main.web-application-type", "none");
        props.put("spring.mail.host", smtpHost);
        props.put("spring.mail.port", smtpPort);

        context = new SpringApplicationBuilder(NotifierApplication.class)
                .properties(props)
                .run();

        notifier = context.getBean(ForRequestingNotifications.class);
    }

    public void tearDown() {
        if (context != null) {
            context.close();
            context = null;
            notifier = null;
        }
    }

}
