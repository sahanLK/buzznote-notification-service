package com.buzznote.notification.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
    public String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
