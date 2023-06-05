package com.im2.brickback.controller.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserLoginResponse {
    private String token;
}
