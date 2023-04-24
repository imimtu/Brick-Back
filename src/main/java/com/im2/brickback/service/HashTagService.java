package com.im2.brickback.service;

import com.im2.brickback.domain.entity.HashTagEntity;
import com.im2.brickback.domain.entity.UserEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.repository.HashTagEntityRepository;
import com.im2.brickback.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final UserEntityRepository userEntityRepository;
    private final HashTagEntityRepository hashTagEntityRepository;

    @Transactional
    public void create(String title, int count, String nickName) {
        // user find
        UserEntity userEntity = userEntityRepository.findByNickName(nickName).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", nickName)));

        // hashtag save
        // TODO : HashTag 중복 등록 방지로직 추가
        hashTagEntityRepository.save(HashTagEntity.of(userEntity, title, count));
    }


}
