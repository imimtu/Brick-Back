package com.im2.brickback.repository;

import com.im2.brickback.domain.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserAccountEntityRepository extends JpaRepository<UserAccountEntity, Long> {
    // userName 기반으로 검색, 없을수도 있으니까 Optional
    Optional<UserAccountEntity> findByUserName(String userName);
}