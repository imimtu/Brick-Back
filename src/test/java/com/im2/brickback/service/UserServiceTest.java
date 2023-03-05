package com.im2.brickback.service;

import com.im2.brickback.domain.entity.UserEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.fixture.UserEntityFixture;
import com.im2.brickback.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityRepository userEntityRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @Test
    void givenNormalUserInfo_whenUserJoin_thenReturnNoException() {
        String userId = "testUserId";
        String userPassword = "testUserPassword";

        when(userEntityRepository.findByUserId(userId)).thenReturn(Optional.empty());
        when(encoder.encode(userPassword)).thenReturn("password_encrypted");
        when(userEntityRepository.save(any())).thenReturn(UserEntityFixture.get(userId, "password_encrypt"));

        Assertions.assertDoesNotThrow(() -> userService.join(userId, userPassword));
    }

    @Test
    void givenDuplicateUserInfo_whenUserJoin_thenReturnDuplicateUserException() {
        String userId = "testUSerId";
        String userPassword = "testUserPassword";

        UserEntity fixture = UserEntityFixture.get(userId, userPassword);

        // TODO : userEntityRepository.findByUserId, userEntityRepository.save(any()) 딥하게 파악
        when(userEntityRepository.findByUserId(userId)).thenReturn(Optional.of(fixture));
        when(encoder.encode(userPassword)).thenReturn("password_encrypted");
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));

        BrickApplicationException exception = Assertions.assertThrows(
                BrickApplicationException.class, () -> userService.join(userId, userPassword)
        );

        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.DUPLICATED_USER_NAME);
    }

}
