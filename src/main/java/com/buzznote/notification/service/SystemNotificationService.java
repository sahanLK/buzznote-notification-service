package com.buzznote.notification.service;

import com.buzznote.notification.config.RabbitConfig;
import com.buzznote.notification.dto.SystemMessage;
import com.buzznote.notification.utils.AuthUtils;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SystemNotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private final AuthUtils authUtils;

    public void sendSystemMessage(SystemMessage message) {
        message.setFrom(authUtils.getEmail());
        rabbitTemplate.convertAndSend(RabbitConfig.SYSTEM_NOTIFICATION_EXCHANGE, RabbitConfig.SYSTEM_NOTIFICATION_ROUTING_KEY, message);
        System.out.println("Message Sent: " + message.getMessage());
    }

}
