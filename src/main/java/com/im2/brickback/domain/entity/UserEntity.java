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
        @Index(columnList = "userId"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
}, name = "user")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String userId;
    @Column(nullable = false) private String userPassword;
    private String nickName;
    private String email;
    private String phoneNumber;
    private boolean isActive;
    @Enumerated(EnumType.STRING) private UserRole role;  // 관리자 권한여부

    @CreatedDate @Column(nullable = false, length = 100) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 누가 했는지는 JpaConfig 에 임시로 달아놓음
    @LastModifiedDate @Column(nullable = false, length = 100) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

    public static UserEntity of(String userId, String userPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setUserPassword(userPassword);
        userEntity.setRole(UserRole.USER); // 회원가입을 통한 유저는 USER-ROLE
        return userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
