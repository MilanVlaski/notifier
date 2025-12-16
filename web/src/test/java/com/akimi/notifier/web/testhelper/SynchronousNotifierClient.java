package com.akimi.notifier.web.testhelper;


import com.akimi.notifier.api.inbound.*;
import com.akimi.notifier.api.values.*;
import com.akimi.notifier.web.*;


import org.apache.hc.client5.http.classic.methods.*;

import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.*;

import org.springframework.boot.builder.*;
import org.springframework.context.*;

import tools.jackson.databind.*;

import java.io.*;

public class SynchronousNotifierClient implements ForRequestingNotifications {

    private ConfigurableApplicationContext context;

    @Override
    public void requestNotification(Notification notification) {
        var mapper = new ObjectMapper();

        HttpPost post = new HttpPost("http://localhost:8080/notification/email");
        post.setEntity(new StringEntity(mapper.writeValueAsString(notification),
                ContentType.APPLICATION_JSON));

        makeRequest(post);
    }

    private static void makeRequest(HttpPost post) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            var response = client.execute(post);

            if (!(response.getCode() >= 200 && response.getCode() <= 300)) {
                throw new RuntimeException("Failure status code: " + response.getCode()
                        + ". Body: " + EntityUtils.toString(response.getEntity()));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUp(String host, int emailPort, String appPort) {
        if (context != null) {
            return;
        }

        System.setProperty("PORT", appPort);
        context = new SpringApplicationBuilder(NotifierApplication.class)
                .run(
                        "--spring.profiles.active=prod,insecure",
                        "--spring.mail.host=" + host,
                        "--spring.mail.port=" + emailPort
                );

    }

    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

}
