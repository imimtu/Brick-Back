package com.im2.brickback.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequest {
    private String userId;
    private String userPassword;
}
