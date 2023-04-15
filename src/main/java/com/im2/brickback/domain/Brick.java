package com.im2.brickback.domain;


import com.im2.brickback.domain.entity.BrickEntity;
import com.im2.brickback.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Brick {

    private Long id;
    private User user;
    private String title;
    private String content;
    private int priority;
    private LocalDateTime deadline;
    private String hashtag;
    private boolean is_completed;

    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime modified_at;
    private String modified_by;

    public static Brick fromEntity(BrickEntity brickEntity){
        return new Brick(
                brickEntity.getId(),
                User.fromEntity(brickEntity.getUser()),
                brickEntity.getTitle(),
                brickEntity.getContent(),
                brickEntity.getPriority(),
                brickEntity.getDeadline(),
                brickEntity.getHashtag(),
                brickEntity.is_completed(),
                brickEntity.getCreated_at(),
                brickEntity.getCreated_by(),
                brickEntity.getModified_at(),
                brickEntity.getModified_by()
        );
    }

}
