package com.akimi.notifier.web.testhelper;


import java.io.IOException;

import com.akimi.notifier.api.values.NotificationRequest;
import com.akimi.notifier.web.NotifierApplication;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import tools.jackson.databind.ObjectMapper;

public class SynchronousNotifierClient {

    private ConfigurableApplicationContext context;

    public void sendEmail(NotificationRequest request) {
        var mapper = new ObjectMapper();

        HttpPost post = new HttpPost("http://localhost:8080/notification");
        post.setEntity(new StringEntity(mapper.writeValueAsString(request),
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
