package com.im2.brickback.service;

import com.im2.brickback.domain.entity.UserEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.fixture.UserEntityFixture;
import com.im2.brickback.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityRepository userEntityRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    // JOIN
    @DisplayName("정상 회원가입")
    @Test
    void givenNormalUserInfo_whenUserJoin_thenReturnNoException() {
        String userId = "testUserId";
        String userPassword = "testUserPassword";

        when(userEntityRepository.findByUserId(userId)).thenReturn(Optional.empty());
        when(encoder.encode(userPassword)).thenReturn("password_encrypted");
        when(userEntityRepository.save(any())).thenReturn(UserEntityFixture.get(userId, "password_encrypt"));

        Assertions.assertDoesNotThrow(() -> userService.join(userId, userPassword));
    }

    @DisplayName("중복 유저로 회원가입 시 중복 유저 예외 발생")
    @Test
    void givenDuplicateUserInfo_whenUserJoin_thenReturnDuplicateUserException() {
        String userId = "testUSerId";
        String userPassword = "testUserPassword";

        when(userEntityRepository.findByUserId(userId)).thenReturn(Optional.of(UserEntityFixture.get(userId, userPassword)));

        BrickApplicationException exception = Assertions.assertThrows(
                BrickApplicationException.class, () -> userService.join(userId, userPassword)
        );

        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.DUPLICATED_USER_NAME);
    }

    // LOGIN
    @DisplayName("정상 로그인")
    @Test
    void givenNormalUserInfo_whenUserLogin_thenReturnNoException() {
        String userId = "testUSerId";
        String userPassword = "testUserPassword";

        UserEntity fixture = UserEntityFixture.get(userId, userPassword);

        // Mocking
        when(userEntityRepository.findByUserId(userId)).thenReturn(Optional.of(fixture));
        when(encoder.matches(fixture.getUserPassword(), userPassword)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> userService.login(userId, userPassword));
    }

    @DisplayName("존재하지 않는 유저로 로그인 시 유저 없음 예외 발생 ")
    @Test
    void givenEmptyUserInfo_whenUserLogin_thenReturnUserNotFoundException() {
        String userId = "testUSerId";
        String userPassword = "testUserPassword";

        // Mocking
        when(userEntityRepository.findByUserId(userId)).thenReturn(Optional.empty());
        BrickApplicationException exception = Assertions.assertThrows(BrickApplicationException.class, () -> userService.login(userId, userPassword));

        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.USER_NOT_FOUND);
    }

    @DisplayName("잘못된 비밀번호로 로그인 시 잘못된 비밀번호 예외 발생 ")
    @Test
    void givenWrongUserPassword_whenUserLogin_thenReturnInvalidPasswordException() {
        String userId = "testUSerId";
        String userPassword = "testUserPassword";
        String wrongUserPassword = "wrongUserPassword";

        UserEntity fixture = UserEntityFixture.get(userId, userPassword);

        // Mocking
        when(userEntityRepository.findByUserId(userId)).thenReturn(Optional.of(fixture));
        BrickApplicationException exception = Assertions.assertThrows(BrickApplicationException.class, () -> userService.login(userId, userPassword));

        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.INVALID_PASSWORD);
    }

}
