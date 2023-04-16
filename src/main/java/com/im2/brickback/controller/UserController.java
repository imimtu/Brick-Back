package com.im2.brickback.controller;

import com.im2.brickback.controller.request.user.UserJoinRequest;
import com.im2.brickback.controller.request.user.UserLoginRequest;
import com.im2.brickback.controller.response.Response;
import com.im2.brickback.controller.response.user.UserJoinResponse;
import com.im2.brickback.controller.response.user.UserLoginResponse;
import com.im2.brickback.domain.User;
import com.im2.brickback.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="user", description = "사용자 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입 진행", tags = { "user" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}) })
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request){
        // 회원가입
        User user = userService.join(request.getUserId(), request.getUserPassword());
        return Response.success(UserJoinResponse.fromUser(user));
    }

    @Operation(summary = "로그인", description = "로그인 진행", tags = { "user" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}) })
    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request){
        // 접근토큰 반환
        String token = userService.login(request.getUserId(), request.getUserPassword());
        return Response.success(new UserLoginResponse(token));
    }

}
