package com.im2.brickback.repository;

import com.im2.brickback.config.JpaConfig;
import com.im2.brickback.domain.entity.BrickEntity;
import com.im2.brickback.domain.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JPARepositoryTest {

    private final BrickEntityRepository brickEntityRepository;
    private final UserEntityRepository userEntityRepository;

    public JPARepositoryTest(
            @Autowired BrickEntityRepository brickEntityRepository,
            @Autowired UserEntityRepository userEntityRepository
    ) {
        this.brickEntityRepository = brickEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @DisplayName("Select Test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {

        // Given

        // When & Then
        List<BrickEntity> bricks = brickEntityRepository.findAll();
        List<UserEntity> users = userEntityRepository.findAll();

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
        long previousCount = brickEntityRepository.count();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime insertDateTime = LocalDateTime.parse("2022-05-25 19:55:00", formatter);

        // When & Then
        BrickEntity tempBrick = BrickEntity.of(any(),"test","test",2, insertDateTime,"test", true);
        BrickEntity savedBrick = brickEntityRepository.save(tempBrick);

        assertThat(brickEntityRepository.count())
                .isEqualTo(previousCount+1);
    }

    @DisplayName("Update Test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {

        // Given
        BrickEntity firstBrick = brickEntityRepository.findById(1L).orElseThrow();
        String updatedContent = "updatedContent";
        firstBrick.setContent("updatedContent");

        // When & Then
        BrickEntity updatedBrick = brickEntityRepository.saveAndFlush(firstBrick);

        assertThat(updatedBrick).hasFieldOrPropertyWithValue("content", updatedContent);
    }

    @DisplayName("Update Test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {

        // Given
        BrickEntity firstBrick = brickEntityRepository.findById(1L).orElseThrow();
        long previousBrickCount = brickEntityRepository.count();

        // When & Then
        brickEntityRepository.delete(firstBrick);

        assertThat(brickEntityRepository.count()).isEqualTo(previousBrickCount-1);
    }


}