package com.im2.brickback.controller.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserLoginRequest {
    private String userId;
    private String userPassword;
}
