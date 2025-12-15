package com.akimi.notifier.web.testhelper;

import com.akimi.notifier.api.values.Message;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MailhogServer {

    private GenericContainer<?> mailhog;
    private String httpBase;

    public void setUp() {
        mailhog = new GenericContainer<>(DockerImageName.parse("mailhog/mailhog:latest"))
                .withExposedPorts(1025, 8025);
        mailhog.start();

        String host = mailhog.getHost();
        Integer smtpPort = mailhog.getMappedPort(1025);
        Integer httpPort = mailhog.getMappedPort(8025);
        this.httpBase = "http://" + host + ":" + httpPort;

        // TODO nonsense
        // Make SMTP settings visible to the client which will boot Spring
        System.setProperty("notifier.smtp.host", host);
        System.setProperty("notifier.smtp.port", smtpPort.toString());
        System.setProperty("notifier.mailhog.http.base", this.httpBase);
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

    public void tearDown() {
        if (mailhog != null) {
            try {
                mailhog.stop();
            } finally {
                mailhog = null;
            }
        }
        System.clearProperty("notifier.smtp.host");
        System.clearProperty("notifier.smtp.port");
        System.clearProperty("notifier.mailhog.http.base");
    }
}
