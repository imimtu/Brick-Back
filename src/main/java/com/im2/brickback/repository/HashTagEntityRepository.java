package com.im2.brickback.repository;

import com.im2.brickback.domain.entity.HashTagEntity;
import com.im2.brickback.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HashTagEntityRepository extends JpaRepository<HashTagEntity, Long> {
    Page<HashTagEntity> findAllByUser(UserEntity userEntity, Pageable pageable);

}
