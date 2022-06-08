package com.nhnacademy.edu.springboot.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NhnAccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void getAccount() {
        List<Account> accounts = accountService.getAccount();

        assertThat(accounts).hasSize(1);
    }
}