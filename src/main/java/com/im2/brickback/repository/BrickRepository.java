package com.im2.brickback.repository;

import com.im2.brickback.domain.Brick;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrickRepository extends JpaRepository<Brick, Long> {
}