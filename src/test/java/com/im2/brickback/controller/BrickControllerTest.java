package com.im2.brickback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.im2.brickback.controller.request.UserJoinRequest;
import com.im2.brickback.domain.User;
import com.im2.brickback.service.BrickService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BrickControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private BrickService brickService;

    @Test
    void test_whenRequest_normalBrickCreateRequest_thenReturn_normalBrickCreateResponse() throws Exception{

    }

}