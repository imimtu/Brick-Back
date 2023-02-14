package com.im2.brickback.domain;

import com.im2.brickback.domain.entity.UserAccountEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserAccount {

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

    public static UserAccount fromEntity(UserAccountEntity userAccount){
        return new UserAccount(
                userAccount.getId(),
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                userAccount.getNickName(),
                userAccount.getEmail(),
                userAccount.getPhoneNumber(),
                userAccount.isActive(),
                userAccount.getRole(),
                userAccount.getCreatedAt(),
                userAccount.getCreatedBy(),
                userAccount.getModifiedAt(),
                userAccount.getModifiedBy()
        );
    }

}
