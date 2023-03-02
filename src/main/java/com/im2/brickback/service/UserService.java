package com.im2.brickback.service;

import com.im2.brickback.domain.User;
import com.im2.brickback.domain.entity.UserEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.repository.BrickRepository;
import com.im2.brickback.repository.UserEntityRepository;
import com.im2.brickback.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("this_is_example_secret_key.for_brick_users_authorization")
    private String secretKey;

    @Value("2592000000")
    private Long expiredTimeMs;

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public User join(String userId, String userPassword) {
        // 유저명 중복인지?
        userEntityRepository.findByUserId(userId).ifPresent(it -> {
            throw new BrickApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated.", userId ));
        });
        // 회원가입 진행
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userId, encoder.encode(userPassword)));
        return User.fromEntity(userEntity);
    }

    @Transactional
    public String login(String userId, String userPassword) {
        // 가입된 유저인지?
        UserEntity userEntity = userEntityRepository.findByUserId(userId).orElseThrow(
                () -> new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not found.", userId))
        );

        // 비밀번호 확인
        if(!encoder.matches(userPassword, userEntity.getUserPassword())){
            throw new BrickApplicationException(ErrorCode.INVALID_PASSWORD);
        }

        // 토큰 생성 후 반환
        String authToken = JwtTokenUtils.generateToken(userId, secretKey, expiredTimeMs);
        return authToken;
    }

}

