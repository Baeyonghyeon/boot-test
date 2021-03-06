package com.nhnacademy.edu.springboot.student.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "com.nhn.account")
@Getter
@Setter
public class AccountAuthorProperties {
    private String author;
}
