package com.buzznote.notification.controller;

import com.buzznote.notification.dto.SystemMessage;
import com.buzznote.notification.service.SystemNotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/message")
@RestController
public class SystemMessageController {

    @Autowired
    private final SystemNotificationService systemNotificationService;

    public SystemMessageController(SystemNotificationService systemNotificationService) {
        this.systemNotificationService = systemNotificationService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody SystemMessage message) {
        systemNotificationService.sendSystemMessage(message);
        return ResponseEntity.ok().body("Notification sent");
    }
}
