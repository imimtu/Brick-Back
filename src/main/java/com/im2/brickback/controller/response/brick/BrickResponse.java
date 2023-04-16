package com.im2.brickback.controller.response.brick;

import com.im2.brickback.domain.Brick;
import com.im2.brickback.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BrickResponse {
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

    public static BrickResponse fromBrick(Brick brick){
        return new BrickResponse(
                brick.getId(),
                brick.getUser(),
                brick.getTitle(),
                brick.getContent(),
                brick.getPriority(),
                brick.getDeadline(),
                brick.getHashtag(),
                brick.is_completed(),
                brick.getCreated_at(),
                brick.getCreated_by(),
                brick.getModified_at(),
                brick.getModified_by()
        );
    }

}
