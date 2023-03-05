package com.im2.brickback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.im2.brickback.controller.request.UserJoinRequest;
import com.im2.brickback.controller.request.UserLoginRequest;
import com.im2.brickback.domain.User;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.service.UserService;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // It provides a way to test your controllers and REST endpoints by sending mock HTTP requests and verifying the responses.

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean // @Autowired is used to inject real beans into a class, while @MockBean is used to inject mock beans for testing purposes.
    private UserService userService; // 협력자

    // JOIN
    @Test
    void test_whenRequest_normalUserJoinRequest_thenReturn_normalUserJoinResponse() throws Exception{
        String userId = "testUserId";
        String userPassword = "testUserPassword";

        when(userService.join(userId, userPassword)).thenReturn(Mockito.mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userId, userPassword)))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void test_whenRequest_invalidUserJoinRequest_thenReturn_error() throws Exception {
        String userId = "testUserId";
        String userPassword = "testUserPassword";

        when(userService.join(userId, userPassword)).thenThrow(new BrickApplicationException(ErrorCode.DUPLICATED_USER_NAME, ""));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userId, userPassword)))
                )
                .andDo(print())
                .andExpect(status().isConflict());
    }

    // LOGIN
    @Test
    void test_whenRequest_normalUserLoginRequest_thenReturn_normalUserLoginResponse() throws Exception {
        String userId = "testUserId";
        String userPassword = "testUserPassword";

        when(userService.login(userId, userPassword)).thenReturn("test_token");

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userId, userPassword)))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void test_whenRequest_wrongPassword_UserLoginRequest_thenReturn_error() throws Exception {
        String userId = "testUserId";
        String userPassword = "testUserPassword";

        when(userService.login(userId, userPassword)).thenThrow(new BrickApplicationException(ErrorCode.INVALID_PASSWORD));

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userId, userPassword)))
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void test_whenRequest_empty_UserLoginRequest_thenReturn_error() throws Exception {
        String userId = "testUserId";
        String userPassword = "testUserPassword";

        when(userService.login(userId, userPassword)).thenThrow(new BrickApplicationException(ErrorCode.USER_NOT_FOUND));

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userId, userPassword)))
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
