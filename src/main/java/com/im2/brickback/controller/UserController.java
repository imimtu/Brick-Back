package com.im2.brickback.controller;

import com.im2.brickback.controller.request.UserJoinRequest;
import com.im2.brickback.controller.response.Response;
import com.im2.brickback.controller.response.UserJoinResponse;
import com.im2.brickback.domain.User;
import com.im2.brickback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request){
        // 회원가입
        User user = userService.join(request.getUserId(), request.getUserPassword());
        return Response.success(UserJoinResponse.fromUser(user));
    }

}
