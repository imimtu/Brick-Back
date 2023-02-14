package com.im2.brickback.service;

import com.im2.brickback.domain.UserAccount;
import com.im2.brickback.domain.entity.UserAccountEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.repository.BrickRepository;
import com.im2.brickback.repository.UserAccountEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserAccountEntityRepository userAccountEntityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BrickRepository brickRepository;

    public UserAccount join(String userName, String password){
        // 유저명 중복인지?
        userAccountEntityRepository.findByUserName(userName).ifPresent( it -> {
                    throw new BrickApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userName ));
                });
        // 회원가입 진행
        UserAccountEntity userAccountEntity = userAccountEntityRepository.save(UserAccountEntity.of(userName, bCryptPasswordEncoder.encode(password)));
        return UserAccount.fromEntity(userAccountEntity);
    }



}
