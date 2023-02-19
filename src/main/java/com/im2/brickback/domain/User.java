package com.im2.brickback.domain;

import com.im2.brickback.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String userId;
    private String userPassword;
    private String nickName;
    private String email;
    private String phoneNumber;
    private boolean isActive;
    private UserRole role;

    private LocalDateTime createdAt;
    private String createdBy; // 누가 했는지는 JpaConfig 에 임시로 달아놓음
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public static User fromEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getUserId(),
                userEntity.getUserPassword(),
                userEntity.getNickName(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber(),
                userEntity.isActive(),
                userEntity.getRole(),
                userEntity.getCreatedAt(),
                userEntity.getCreatedBy(),
                userEntity.getModifiedAt(),
                userEntity.getModifiedBy()
        );
    }

}
