package com.akimi.notifier.web.controllers;

import com.akimi.notifier.api.inbound.*;
import com.akimi.notifier.api.values.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotificationController {

    private final ForRequestingNotifications notifier;

    public NotificationController(ForRequestingNotifications notifier) {
        this.notifier = notifier;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendNotification(
            @RequestBody NotificationRequest request
    ) {
        notifier.requestNotification(request.channel, request.to, request.message);
        return ResponseEntity.accepted().build();
    }

    public record NotificationRequest(Channel channel, Message message, To to) {

    }

}
