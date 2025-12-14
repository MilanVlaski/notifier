package com.akimi.notifier.api;

public class Notifier implements ForRequestingNotifications {


    private final ForSendingNotifications forSendingNotifications;

    public Notifier(ForSendingNotifications forSendingNotifications) {
        this.forSendingNotifications = forSendingNotifications;
    }


    @Override
    public void requestNotification() {
        forSendingNotifications.sendNotification();
    }
}
