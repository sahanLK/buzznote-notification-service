package com.buzznote.notification.controller;

import com.buzznote.notification.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailController {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleResetPasswordEmail(Map<String, String> message) {
        String to = message.get("to");
        String token = message.get("token");

        // Simulate sending email
        System.out.println("Sending password reset email to: " + to);
        System.out.println("Reset link: https://your-app/reset?token=" + token);
    }

}
