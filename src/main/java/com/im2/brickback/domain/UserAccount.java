package com.im2.brickback.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Table(indexes = {
        @Index(columnList = "user_id"),
        @Index(columnList = "created_at"),
        @Index(columnList = "created_by")
})
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String user_id;
    @Setter @Column(nullable = false) private String user_password;
    @Setter private String nickname;
    @Setter private String email;
    @Setter private String phone_number;
    @Setter private boolean is_active;

    @CreatedDate @Column(nullable = false, length = 100) private LocalDateTime created_at;
    @CreatedBy @Column(nullable = false, length = 100) private String created_by; // 누가 했는지는 JpaConfig 에 임시로 달아놓음
    @LastModifiedDate @Column(nullable = false, length = 100) private LocalDateTime modified_at;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modified_by;

    protected UserAccount() {}
    public UserAccount(String user_id, String user_password, String nickname, String email, String phone_number, boolean is_active) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.nickname = nickname;
        this.email = email;
        this.phone_number = phone_number;
        this.is_active = is_active;
    }

    public static UserAccount of(String user_id, String user_password, String nickname, String email, String phone_number, boolean is_active) {
        return new UserAccount( user_id, user_password, nickname, email, phone_number, is_active);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
