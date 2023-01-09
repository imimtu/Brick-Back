package com.im2.brickback.repository;

import com.im2.brickback.config.JpaConfig;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JPARepositoryTest {

    private final BrickRepository brickRepository;
    private final UserAccountRepository userAccountRepository;

    public JPARepositoryTest(BrickRepository brickRepository, UserAccountRepository userAccountRepository) {
        this.brickRepository = brickRepository;
        this.userAccountRepository = userAccountRepository;
    }
}