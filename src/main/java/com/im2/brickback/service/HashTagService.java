package com.im2.brickback.service;

import com.im2.brickback.domain.HashTag;
import com.im2.brickback.domain.entity.HashTagEntity;
import com.im2.brickback.domain.entity.UserEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.repository.HashTagEntityRepository;
import com.im2.brickback.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    public Page<HashTag> list(String nickName, Pageable pageable) {
        UserEntity userEntity = userEntityRepository.findByNickName(nickName).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", nickName)));

        return hashTagEntityRepository.findAllByUser(userEntity, pageable).map(HashTag::fromEntity);
    }


    @Transactional
    public HashTag modify(String title, int count, String nickName, Long hashTagId) {
        // user exist
        UserEntity userEntity = userEntityRepository.findByNickName(nickName).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("nickName %s is not valid", nickName)));

        // hashtag exist
        HashTagEntity hashTagEntity = hashTagEntityRepository.findById(hashTagId).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.HASHTAG_NOT_FOUND, String.format("hashTag %d is not founded", hashTagId)));

        if(hashTagEntity.getUser() != userEntity){
            throw new BrickApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %d", nickName, hashTagId));
        }

        hashTagEntity.setTitle(title);
        hashTagEntity.setCount(count);

        return HashTag.fromEntity(hashTagEntityRepository.saveAndFlush(hashTagEntity));
    }

    @Transactional
    public void delete(String nickName, Long hashTagId) {
        // user exist
        UserEntity userEntity = userEntityRepository.findByNickName(nickName).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("nickName %d is not valid", nickName)));

        // hashtag exist
        HashTagEntity hashTagEntity = hashTagEntityRepository.findById(hashTagId).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.HASHTAG_NOT_FOUND, String.format("hashTag %d is not founded", hashTagId)));

        // has permission
        if(hashTagEntity.getUser() != userEntity){
            throw new BrickApplicationException(ErrorCode.INVALID_PERMISSION,  String.format("%s has no permission with %d", nickName, hashTagId));
        }

        hashTagEntityRepository.delete(hashTagEntity);
    }
}
