package com.buzznote.notification.controller;

import com.buzznote.notification.config.RabbitConfig;
import com.buzznote.notification.dto.SystemMessage;
import com.buzznote.notification.dto.SystemNotification;
import com.buzznote.notification.service.SystemNotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final SystemNotificationService systemNotificationService;

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody SystemMessage message) {
        systemNotificationService.sendSystemMessage(message);
        return ResponseEntity.ok().body("Message sent");
    }

    @PostMapping("/notification")
    public ResponseEntity<?> sendNotification(@Valid @RequestBody SystemNotification notification) {
        systemNotificationService.sendSystemNotification(notification);
        return ResponseEntity.ok().body("Notification sent");
    }
}
