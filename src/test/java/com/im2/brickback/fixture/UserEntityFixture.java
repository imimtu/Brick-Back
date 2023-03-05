package com.im2.brickback.fixture;

import com.im2.brickback.domain.entity.UserEntity;

public class UserEntityFixture {
    public static UserEntity get(String userId, String userPassword){
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setUserId(userId);
        userEntity.setUserPassword(userPassword);

        return  userEntity;
    }
}
