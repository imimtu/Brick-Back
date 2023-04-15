package com.im2.brickback.domain;

import com.im2.brickback.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class User implements UserDetails {

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.nickName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
