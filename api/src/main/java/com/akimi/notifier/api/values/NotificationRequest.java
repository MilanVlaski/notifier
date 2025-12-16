package com.akimi.notifier.api.values;


public record NotificationRequest(
        Channel channel, String from, String to, Message message
) {
}
