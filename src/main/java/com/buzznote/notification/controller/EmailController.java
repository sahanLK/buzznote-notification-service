package com.buzznote.notification.controller;

import com.buzznote.notification.config.RabbitConfig;
import com.buzznote.notification.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

//    @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
//    public void handleResetPasswordEmail(Map<String, String> message) throws IOException {
//        emailService.sendPasswordResetEmail(message);
//    }

}
