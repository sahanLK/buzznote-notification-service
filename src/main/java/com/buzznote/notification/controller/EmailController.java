package com.buzznote.notification.controller;

import com.buzznote.notification.config.RabbitConfig;
import okhttp3.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
public class EmailController {

    @Autowired
    private Environment env;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleResetPasswordEmail(Map<String, String> message) throws IOException {
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
                .url("https://api.brevo.com/v3/smtp/email")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("api-key", Objects.requireNonNull(env.getProperty("MAIL_API_KEY")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                System.out.println("Response: " + response.body().string());
            } else {
                System.err.println("Failed: " + response.code());
                assert response.body() != null;
                System.err.println("Response: " + response.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
