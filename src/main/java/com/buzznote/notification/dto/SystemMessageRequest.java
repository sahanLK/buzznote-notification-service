package com.buzznote.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SystemMessageRequest {

    @Email(message = "please enter a valid email")
    private String from;

    @Email(message = "please enter a valid email")
    @NotBlank(message = "please specify the recipient")
    private String to;

    @NotBlank(message = "message should not be empty")
    @Size(message = "Message can contain maximum of 200 characters", max = 200)
    private String message;

}
