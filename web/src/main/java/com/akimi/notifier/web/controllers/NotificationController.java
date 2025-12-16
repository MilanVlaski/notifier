package com.akimi.notifier.web.controllers;

import com.akimi.notifier.api.inbound.ForRequestingNotifications;
import com.akimi.notifier.api.values.NotificationRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NotificationController {

    private final ForRequestingNotifications notifier;

    public NotificationController(ForRequestingNotifications notifier) {
        this.notifier = notifier;
    }

    @PostMapping("/notification")
    public ResponseEntity<Void> sendNotification(
            @RequestBody NotificationRequest request
    ) {
        notifier.requestNotification(request);
        return ResponseEntity.accepted().build();
    }

}
