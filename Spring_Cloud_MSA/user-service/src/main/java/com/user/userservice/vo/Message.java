package com.user.userservice.vo;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Message {

    @Value("${testMessage}")
    private String message;
}
