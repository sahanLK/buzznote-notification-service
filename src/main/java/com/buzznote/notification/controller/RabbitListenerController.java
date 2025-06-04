package com.buzznote.notification.controller;

import com.buzznote.notification.config.RabbitConfig;
import com.buzznote.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RabbitListenerController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitListenerController.class);
    private final EmailService emailService;

    @RabbitListener(queues = RabbitConfig.PASSWORD_RESET_EMAIL_QUEUE)
    public void passwordResetEmailListener(Map<String, String> message) throws IOException {
        emailService.sendPasswordResetEmail(message);
    }

}
