package com.akimi.notifier.api;

import com.akimi.notifier.api.outbound.*;
import com.akimi.notifier.api.values.*;

public class Broker {
    private final ForSendingNotifications[] notificationSenders;

    public Broker(ForSendingNotifications... notificationSenders) {
        this.notificationSenders = notificationSenders;
    }


    public void breakMessage(Channel channel, From from, To to, Message message) {
        var sender = findSenderForChannel(channel);
        if (sender != null) {
            sender.sendNotification(from, to, message);
        } else {
           throw new RuntimeException("Sender not found for channel " + channel);
        }
    }

    private ForSendingNotifications findSenderForChannel(Channel channel) {
        for (ForSendingNotifications sender : notificationSenders) {
            if (sender.channel().equals(channel)) {
                return sender;
            }
        }
        return null;
    }
}
