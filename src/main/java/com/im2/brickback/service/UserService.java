package com.im2.brickback.service;

import com.im2.brickback.domain.User;
import com.im2.brickback.domain.entity.UserEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.repository.BrickRepository;
import com.im2.brickback.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder encoder;
//    private final BrickRepository brickRepository;

    public User join(String userId, String userPassword){
        // 유저명 중복인지?
        userEntityRepository.findByUserId(userId).ifPresent(it -> {
            throw new BrickApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userId ));
        });
        // 회원가입 진행
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userId, encoder.encode(userPassword)));
        return User.fromEntity(userEntity);
    }

}

