package com.nhnacademy.edu.springboot.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testAccountRepository(){
        Account kurt = new Account(1L, "kurt", 3_0000);
        accountRepository.save(kurt);

        Optional<Account> account = accountRepository.findById(1L);

        assertThat(account).isPresent();
        assertThat(account.orElse(null)).isEqualTo(kurt);
    }
}