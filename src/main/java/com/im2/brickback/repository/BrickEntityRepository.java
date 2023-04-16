package com.im2.brickback.repository;

import com.im2.brickback.domain.entity.BrickEntity;
import com.im2.brickback.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BrickEntityRepository extends JpaRepository<BrickEntity, Long> {
    Page<BrickEntity> findAllByUser(UserEntity userEntity, Pageable pageable);
}