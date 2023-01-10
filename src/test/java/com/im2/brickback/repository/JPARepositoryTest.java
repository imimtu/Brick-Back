package com.im2.brickback.repository;

import com.im2.brickback.config.JpaConfig;
import com.im2.brickback.domain.Brick;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JPARepositoryTest {

    private final BrickRepository brickRepository;
    private final UserAccountRepository userAccountRepository;

    public JPARepositoryTest(
            @Autowired BrickRepository brickRepository,
            @Autowired UserAccountRepository userAccountRepository
    ) {
        this.brickRepository = brickRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("Select Test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {

        // Given

        // When & Then
        List<Brick> bricks = brickRepository.findAll();

        assertThat(bricks)
                .isNotNull()
                .hasSize(0);

    }

}