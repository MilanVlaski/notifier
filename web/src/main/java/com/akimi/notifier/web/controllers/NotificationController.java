package com.akimi.notifier.web.controllers;

import com.akimi.notifier.api.inbound.*;
import com.akimi.notifier.api.values.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

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
