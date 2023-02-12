package com.im2.brickback.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// @WebMvcTestg
@DisplayName("DATA REST - API 테스트")
@Transactional // Test 에서의 Transactional 기본 동작은 roll-back 으로 transaction 이 묶인다.
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] Brick 단건 조회, 리스트 조회")
    @Test
    void givenNothing_whenRequestingBricks_thenReturnsBricksJsonResponse() throws Exception {
        // Given
        // WhenThen
        mvc.perform( get("/api/bricks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());

        mvc.perform(get("/api/bricks/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }

    @DisplayName("[api] UserAccount 단건 조회, 리스트 조회")
    @Test
    void givenNothing_whenRequestingUserAccounts_thenReturnsUserAccountsJsonResponse() throws Exception {
        // Given
        // WhenThen
        mvc.perform( get("/api/userAccounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());

        mvc.perform(get("/api/userAccounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }




}
