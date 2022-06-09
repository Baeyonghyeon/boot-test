package com.nhnacademy.edu.springboot.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerRandomPostTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private Integer randomPort;

    @Test
    void testRestGetAccounts() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Account>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Account>> accounts = restTemplate.exchange("/accounts"
                , HttpMethod.GET
                , httpEntity
                , new ParameterizedTypeReference<List<Account>>() {
                });

        assertThat(accounts.getBody()).isEqualTo(List.of(new Account(1L, "kurt", 3_0000)));
    }

    @Test
    void testRestGetAccount() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Account> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Account> accounts = restTemplate.exchange("/accounts/{id}"
                , HttpMethod.GET
                , httpEntity
                , new ParameterizedTypeReference<Account>() {
                }
                , 1L);

        assertThat(accounts.getBody())
                .isEqualTo(new Account(1L, "kurt", 3_0000));
    }
}
