package com.im2.brickback.repository;

import com.im2.brickback.domain.entity.BrickEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BrickRepository extends JpaRepository<BrickEntity, Long> {
}