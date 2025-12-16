package com.akimi.notifier.api.values;

public record EmailNotification(From from, To to, Message message)
        implements Notification {
}
