package com.im2.brickback.controller.response;


import com.im2.brickback.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponse {
    private Long id;
    private String userId;
    private String userPassword;

    public static UserJoinResponse fromUser(User user){
        return new UserJoinResponse(
                user.getId(),
                user.getUserId(),
                user.getUserPassword()
        );
    }
}
