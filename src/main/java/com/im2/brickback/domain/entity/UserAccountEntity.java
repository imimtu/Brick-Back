package com.im2.brickback.domain.entity;

import com.im2.brickback.domain.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Table(indexes = {
        @Index(columnList = "user_id"),
        @Index(columnList = "created_at"),
        @Index(columnList = "created_by")
}, name = "user_account")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String user_id;
    @Setter @Column(nullable = false) private String user_password;
    @Setter private String nickname;
    @Setter private String email;
    @Setter private String phone_number;
    @Setter private boolean is_active;
    @Column(name = "role") @Enumerated(EnumType.STRING) private UserRole role;  // 관리자 권한여부

    @CreatedDate @Column(nullable = false, length = 100) private LocalDateTime created_at;
    @CreatedBy @Column(nullable = false, length = 100) private String created_by; // 누가 했는지는 JpaConfig 에 임시로 달아놓음
    @LastModifiedDate @Column(nullable = false, length = 100) private LocalDateTime modified_at;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modified_by;

    protected UserAccountEntity() {}
    public UserAccountEntity(String user_id, String user_password, String nickname, String email, String phone_number, boolean is_active, UserRole userRole) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.nickname = nickname;
        this.email = email;
        this.phone_number = phone_number;
        this.is_active = is_active;
        this.role = userRole;
    }

    public static UserAccountEntity of(String user_id, String user_password, String nickname, String email, String phone_number, boolean is_active, UserRole userRole) {
        return new UserAccountEntity( user_id, user_password, nickname, email, phone_number, is_active, userRole);
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
