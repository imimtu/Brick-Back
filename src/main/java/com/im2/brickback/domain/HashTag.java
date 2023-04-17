package com.im2.brickback.domain;

import com.im2.brickback.domain.entity.HashTagEntity;
import com.im2.brickback.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class HashTag {

    private Long id;
    private User user;
    private int count;

    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime modified_at;
    private String modified_by;

    public static HashTag fromEntity(HashTagEntity hashTagEntity){
        return new HashTag(
                hashTagEntity.getId(),
                User.fromEntity(hashTagEntity.getUser()),
                hashTagEntity.getCount(),
                hashTagEntity.getCreated_at(),
                hashTagEntity.getCreated_by(),
                hashTagEntity.getModified_at(),
                hashTagEntity.getModified_by()
        );
    }

}
