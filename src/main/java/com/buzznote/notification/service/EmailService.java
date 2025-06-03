package com.buzznote.notification.service;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private Environment env;

    public void sendPasswordResetEmail(Map<String, String> message) {
        String to = message.get("to");
        String token = message.get("token");

        OkHttpClient client = new OkHttpClient();
        String json = String.format("""
                 {
                    "to":[
                       {
                          "email":"%s"
                       }
                    ],
                    "templateId": 1,
                    "params":{
                       "reset_link": "%s"
                    },
                    "headers":{
                       "X-Mailin-custom":"custom_header_1:custom_value_1|custom_header_2:custom_value_2|custom_header_3:custom_value_3",
                       "charset":"iso-8859-1"
                    }
                 }
                """, to, token);


        RequestBody body = RequestBody.create(
                json, MediaType.get("application/json")
        );

        Request request = new Request.Builder()
                .url(Objects.requireNonNull(env.getProperty("MAIL_SERVER_API")))
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("api-key", Objects.requireNonNull(env.getProperty("MAIL_API_KEY")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                logger.info("Password reset email sent successfully: {}", to);
            } else {
                logger.info("Failed to send password reset email: {} -> {}", to, response.body());
            }
        } catch (IOException e) {
            logger.error("Email send failure: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
