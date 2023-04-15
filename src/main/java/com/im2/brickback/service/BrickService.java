package com.im2.brickback.service;

import com.im2.brickback.domain.User;
import com.im2.brickback.domain.entity.BrickEntity;
import com.im2.brickback.domain.entity.UserEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.repository.BrickEntityRepository;
import com.im2.brickback.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BrickService {

    private final UserEntityRepository userEntityRepository;
    private final BrickEntityRepository brickEntityRepository;

    @Transactional
    public void create(String title, String content, int priority, LocalDateTime deadline, String hashtag, String nickName) {
        // user find
        UserEntity userEntity = userEntityRepository.findByNickName(nickName).orElseThrow(() ->
                new BrickApplicationException(ErrorCode.INVALID_PERMISSION, String.format("nickName %d is not valid", nickName)));
        // brick save
        brickEntityRepository.save(BrickEntity.of(userEntity,title,content,priority,deadline,hashtag,false)); // 초기 생성시에 is_completed 는 false
    }


}
