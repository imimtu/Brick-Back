package com.im2.brickback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.im2.brickback.controller.request.brick.BrickCreateRequest;

import com.im2.brickback.service.BrickService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BrickControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BrickService brickService;

    // CREATE
    @Test
    @WithMockUser
    void test_whenRequest_normalBrickCreateRequest_thenReturn_normalBrickCreateResponse() throws Exception{
        String title = "test-title";
        String content = "test-content";
        int priority = 1;
        String dateTime = "20220920210200";
        String hashtag = "hashtag";

        SecurityContext context = SecurityContextHolder.getContext();
        context.getAuthentication();

        mockMvc.perform(
                post("/api/v1/bricks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new BrickCreateRequest(title, content, priority, dateTime, hashtag)))
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void test_whenRequest_normalBrickCreateRequestWithoutLogin_thenReturn_UnAuthorizedException() throws Exception{
        String title = "test-title";
        String content = "test-content";
        int priority = 1;
        String dateTime = "20220920210200";
        String hashtag = "hashtag";

//        SecurityContext context = SecurityContextHolder.getContext();
//        context.getAuthentication();

        // TODO : (debug) why authentication is null?
        mockMvc.perform(
                        post("/api/v1/bricks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(new BrickCreateRequest(title, content, priority, dateTime, hashtag)))
                ).andDo(print())
                .andExpect(status().isUnauthorized());
    }

    // READ
    @Test
    @WithMockUser
    void test_whenRequest_normalBrickList_thenReturn_allBrickList() throws Exception{

        when(brickService.list(any(), any())).thenReturn(Page.empty());

        mockMvc.perform(
                        get("/api/v1/bricks/list")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void test_whenRequest_normalBrickListWithoutLogin_thenReturn_UnAuthorizedException() throws Exception{

        when(brickService.list(any(), any())).thenReturn(Page.empty());

        mockMvc.perform(
                        get("/api/v1/bricks/list")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isUnauthorized());
    }

    // UPDATE


    // DELETE


}