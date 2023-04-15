package com.im2.brickback.repository;

import com.im2.brickback.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    // userId 기반으로 검색, 없을수도 있으니까 Optional
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByNickName(String nickName);
}