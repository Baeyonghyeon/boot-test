package com.nhnacademy.edu.springboot.student;

import com.nhnacademy.edu.springboot.student.config.AccountAuthorProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.SystemProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountWebController.class)
class AccountWebControllerTest {

    @Autowired
    MockMvc mockMvc;

    @SpyBean
    AccountAuthorProperties accountAuthorProperties;

    @Test
    void getStudent() throws Exception{
        given(accountAuthorProperties.getAuthor())
                .willReturn("ABCDEFG");

        mockMvc.perform(get("/web/author"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.author", equalTo("ABCDEFG")));
    }
}