package com.im2.brickback.domain.entity;

import com.im2.brickback.domain.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Table(indexes = {
        @Index(columnList = "user_id"),
        @Index(columnList = "created_at"),
        @Index(columnList = "created_by")
}, name = "user_account")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String userId;
    @Column(nullable = false) private String userPassword;
    private String nickname;
    private String email;
    private String phoneNumber;
    private boolean isActive;
    @Enumerated(EnumType.STRING) private UserRole role;  // 관리자 권한여부

    @CreatedDate @Column(nullable = false, length = 100) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 누가 했는지는 JpaConfig 에 임시로 달아놓음
    @LastModifiedDate @Column(nullable = false, length = 100) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

//    public UserAccountEntity(String user_id, String user_password, String nickname, String email, String phone_number, boolean is_active, UserRole userRole) {
//        this.user_id = user_id;
//        this.user_password = user_password;
//        this.nickname = nickname;
//        this.email = email;
//        this.phone_number = phone_number;
//        this.is_active = is_active;
//        this.role = userRole;
//    }

    public static UserAccountEntity of(String userId, String userPassword) {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setUserId(userId);
        userAccountEntity.setUserPassword(userPassword);
        return userAccountEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccountEntity that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
