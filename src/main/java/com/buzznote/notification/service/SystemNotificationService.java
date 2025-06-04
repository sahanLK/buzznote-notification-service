package com.buzznote.notification.service;

import com.buzznote.notification.config.RabbitConfig;
import com.buzznote.notification.dto.SystemMessageRequest;
import com.buzznote.notification.dto.SystemNotificationRequest;
import com.buzznote.notification.model.Notification;
import com.buzznote.notification.model.SystemMessage;
import com.buzznote.notification.repo.NotificationRepo;
import com.buzznote.notification.repo.SystemMessageRepo;
import com.buzznote.notification.utils.AuthUtils;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SystemNotificationService {

    private final RabbitTemplate rabbitTemplate;
    private final SystemMessageRepo systemMessageRepo;
    private final NotificationRepo notificationRepo;

    @Autowired
    private final AuthUtils authUtils;

    public void sendSystemMessage(SystemMessageRequest message) {
        message.setFrom(authUtils.getEmail());
        rabbitTemplate.convertAndSend(RabbitConfig.SYSTEM_MESSAGE_EXCHANGE, RabbitConfig.SYSTEM_MESSAGE_ROUTING_KEY, message);
        System.out.println("Message sent: " + message.getMessage());
    }

    public void sendSystemNotification(SystemNotificationRequest notification) {
        rabbitTemplate.convertAndSend(RabbitConfig.SYSTEM_NOTIFICATION_EXCHANGE, RabbitConfig.SYSTEM_NOTIFICATION_ROUTING_KEY, notification);
        System.out.println("Notification sent: " + notification.getMessage());
    }

    public void saveSystemMessage(SystemMessageRequest message) {
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.setSender(authUtils.getEmail());
        systemMessage.setMessage(message.getMessage());
        systemMessage.setReceiver(message.getTo());
        systemMessageRepo.save(systemMessage);
    }

    public void saveSystemNotification(@Valid SystemNotificationRequest notification) {
        Notification systemNotification = new Notification();
        systemNotification.setBody(notification.getMessage());
        systemNotification.setTitle(notification.getTitle());
        notificationRepo.save(systemNotification);
    }
}
