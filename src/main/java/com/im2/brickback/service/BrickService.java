package com.im2.brickback.service;

import com.im2.brickback.domain.Brick;
import com.im2.brickback.domain.entity.BrickEntity;
import com.im2.brickback.domain.entity.UserEntity;
import com.im2.brickback.exception.BrickApplicationException;
import com.im2.brickback.exception.ErrorCode;
import com.im2.brickback.repository.BrickEntityRepository;
import com.im2.brickback.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        UserEntity userEntity = userEntityRepository.findByNickName(nickName).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", nickName)));
        // brick save
        brickEntityRepository.save(BrickEntity.of(userEntity,title,content,priority,deadline,hashtag,false)); // 초기 생성시에 is_completed 는 false
    }

    @Transactional
    public Page<Brick> list(String name, Pageable pageable) {
        UserEntity userEntity = userEntityRepository.findByNickName(name).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", name)));

        return brickEntityRepository.findAllByUser(userEntity, pageable).map(Brick::fromEntity);
    }

    @Transactional
    public Brick modify(String title, String content, int priority, LocalDateTime deadline, String hashtag, String nickName, Long brickId) {
        // user exist
        UserEntity userEntity = userEntityRepository.findByNickName(nickName).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("nickName %d is not valid", nickName)));

        // brick exist
        BrickEntity brickEntity =  brickEntityRepository.findById(brickId).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.BRICK_NOT_FOUND, String.format("brick ID: %d is not found", brickId)));

        // has permission
        if(brickEntity.getUser() != userEntity){
            throw new BrickApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %d", nickName, brickId));
        }

        brickEntity.setTitle(title);
        brickEntity.setContent(content);
        brickEntity.setPriority(priority);
        brickEntity.setDeadline(deadline);
        brickEntity.setHashtag(hashtag);

        return Brick.fromEntity(brickEntityRepository.saveAndFlush(brickEntity));
    }

    @Transactional
    public void delete(String nickName, Long brickId) {
        // user exist
        UserEntity userEntity = userEntityRepository.findByNickName(nickName).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.USER_NOT_FOUND, String.format("nickName %d is not valid", nickName)));

        // brick exist
        BrickEntity brickEntity =  brickEntityRepository.findById(brickId).orElseThrow( () ->
                new BrickApplicationException(ErrorCode.BRICK_NOT_FOUND, String.format("brick ID: %d is not found", brickId)));

        // has permission
        if(brickEntity.getUser() != userEntity){
            throw new BrickApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %d", nickName, brickId));
        }

        brickEntityRepository.delete(brickEntity);
    }

}
