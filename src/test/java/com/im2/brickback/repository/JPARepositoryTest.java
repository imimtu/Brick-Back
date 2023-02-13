package com.im2.brickback.repository;

import com.im2.brickback.config.JpaConfig;
import com.im2.brickback.domain.entity.BrickEntity;
import com.im2.brickback.domain.entity.UserAccountEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        List<BrickEntity> bricks = brickRepository.findAll();
        List<UserAccountEntity> users = userAccountRepository.findAll();

        assertThat(bricks)
                .isNotNull()
                .hasSize(   100);

        assertThat(users)
                .isNotNull()
                .hasSize(0);
    }

    @DisplayName("Insert Test")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {

        // Given
        long previousCount = brickRepository.count();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime insertDateTime = LocalDateTime.parse("2022-05-25 19:55:00", formatter);

        // When & Then
        BrickEntity tempBrick = BrickEntity.of("test","test",2, insertDateTime,"test", true);
        BrickEntity savedBrick = brickRepository.save(tempBrick);

        assertThat(brickRepository.count())
                .isEqualTo(previousCount+1);
    }

    @DisplayName("Update Test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {

        // Given
        BrickEntity firstBrick = brickRepository.findById(1L).orElseThrow();
        String updatedContent = "updatedContent";
        firstBrick.setContent("updatedContent");

        // When & Then
        BrickEntity updatedBrick = brickRepository.saveAndFlush(firstBrick);

        assertThat(updatedBrick).hasFieldOrPropertyWithValue("content", updatedContent);
    }

    @DisplayName("Update Test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {

        // Given
        BrickEntity firstBrick = brickRepository.findById(1L).orElseThrow();
        long previousBrickCount = brickRepository.count();

        // When & Then
        brickRepository.delete(firstBrick);

        assertThat(brickRepository.count()).isEqualTo(previousBrickCount-1);
    }


}