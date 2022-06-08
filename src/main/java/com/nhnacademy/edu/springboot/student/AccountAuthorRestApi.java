package com.nhnacademy.edu.springboot.student;

import com.nhnacademy.edu.springboot.student.config.AccountAuthorProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountAuthorRestApi {

    private final AccountAuthorProperties accountAuthorProperties;

    @GetMapping("/system/author")
    public AccountAuthorProperties getAuthor(){
        return accountAuthorProperties;
    }


}
