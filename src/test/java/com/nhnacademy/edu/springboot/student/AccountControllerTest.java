package com.nhnacademy.edu.springboot.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//테스트시 default가 MOCK이라 사실 설정할 필요 없음.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //Order 순서대로 하고 싶다면 추가
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @SpyBean
    private AccountRepository accountRepository;

    @Test
    @Order(1)
    void getAccounts() throws Exception{
        given(accountRepository.findAll())
                .willReturn(List.of(new Account(1L,"kurt", 3_0000), new Account(2L,"kkkkk", 2_0000)));
        this.mvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", equalTo("kurt")));
    }

    @Test
    @Order(4)
    void getAccount() throws Exception {
        Account account = new Account(1L, "kurt", 3_0000);

        given(accountRepository.findById(1L))
                .willReturn(Optional.of(account));
        this.mvc.perform(get("/accounts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name",equalTo("kurt")))
                .andExpect(jsonPath("$.score",equalTo(3_0000)));
    }

    @Test
    @Order(2)
    void createAccount() throws Exception {
        Account account = new Account(2L, "coco", 100);
        given(accountRepository.save(account))
                .willReturn(account);

        String requestBody = new ObjectMapper().writeValueAsString(account);

        this.mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(2)));
    }

    @Test
    @Order(3)
    void deleteAccount() throws Exception {
        willDoNothing().given(accountRepository).deleteById(2L);

        this.mvc.perform(delete("/accounts/{id}", 2L))
                .andExpect(status().isOk());
    }
}