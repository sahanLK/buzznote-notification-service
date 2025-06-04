package com.buzznote.notification.controller;

import com.buzznote.notification.dto.SystemMessageRequest;
import com.buzznote.notification.dto.SystemNotificationRequest;
import com.buzznote.notification.service.SystemNotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final SystemNotificationService systemNotificationService;

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody SystemMessageRequest message) {
        systemNotificationService.saveSystemMessage(message);
        systemNotificationService.sendSystemMessage(message);
        return ResponseEntity.ok().body("Message sent");
    }

    @PostMapping("/notification")
    public ResponseEntity<?> sendNotification(@Valid @RequestBody SystemNotificationRequest notification) {
        systemNotificationService.saveSystemNotification(notification);
        systemNotificationService.sendSystemNotification(notification);
        return ResponseEntity.ok().body("Notification sent");
    }
}
