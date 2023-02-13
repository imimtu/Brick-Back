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
    private String user_id;
    private String user_password;
    private String nickname;
    private String email;
    private String phone_number;
    private boolean is_active;

    private LocalDateTime created_at;
    private String created_by; // 누가 했는지는 JpaConfig 에 임시로 달아놓음
    private LocalDateTime modified_at;
    private String modified_by;

    public static UserAccount fromEntity(UserAccountEntity userAccount){
        return new UserAccount(
                userAccount.getId(),
                userAccount.getUser_id(),
                userAccount.getUser_password(),
                userAccount.getNickname(),
                userAccount.getEmail(),
                userAccount.getPhone_number(),
                userAccount.is_active(),
                userAccount.getCreated_at(),
                userAccount.getCreated_by(),
                userAccount.getModified_at(),
                userAccount.getModified_by()
        );
    }

}
