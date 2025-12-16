package com.akimi.notifier.web.testhelper;

import java.net.URI;
import java.net.http.*;

import com.akimi.notifier.api.values.Message;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class MailhogServer {

    private GenericContainer<?> mailhog;
    private String httpBase;
    private String smtpHost;
    private int smtpPort;

    public void setUp(int emailPort, int httpTestPort) {
        mailhog = new GenericContainer<>(DockerImageName.parse("mailhog/mailhog:latest"))
                .withExposedPorts(emailPort, httpTestPort);
        mailhog.start();

        String host = mailhog.getHost();
        Integer smtpPort = mailhog.getMappedPort(emailPort);
        Integer httpPort = mailhog.getMappedPort(httpTestPort);
        this.smtpHost = host;
        this.smtpPort = smtpPort;
        this.httpBase = "http://" + host + ":" + httpPort;
    }

    public void hasReceivedMessage(Message message) {
        String expected = message.body();
        HttpClient client = HttpClient.newHttpClient();

        int attempts = 30;
        long pauseMillis = 200;

        for (int i = 0; i < attempts; i++) {
            try {
                HttpRequest req = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(httpBase + "/api/v2/messages"))
                        .build();
                HttpResponse<String> resp = client.send(req,
                        HttpResponse.BodyHandlers.ofString());
                if (resp.statusCode() == 200) {
                    String body = resp.body();
                    if (body != null && body.contains(expected)) {
                        return;
                    }
                }
                Thread.sleep(pauseMillis);
            } catch (Exception ignored) {
                try {
                    Thread.sleep(pauseMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("Message has not been received.");
    }

    public String smtpHost() {
        return smtpHost;
    }

    public int smtpPort() {
        return smtpPort;
    }

    public void tearDown() {
        if (mailhog != null) {
            mailhog.stop();
        }
    }
}
