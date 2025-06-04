package com.buzznote.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SystemNotificationRequest {
    @NotBlank(message = "Title cannot be empty")
    @Size(message = "Title can contain maximum of 100 characters", max = 100)
    private String title;

    @NotBlank(message = "Message cannot be blank")
    @Size(message = "Message can contain maximum of 100 characters", max = 100)
    private String message;
}
