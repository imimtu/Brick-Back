package com.im2.brickback.controller.response.hashtag;

import com.im2.brickback.domain.HashTag;
import com.im2.brickback.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class HashTagResponse {

    private Long id;
    private User user;
    private String title;
    private int count;
    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime modified_at;
    private String modified_by;

    public static HashTagResponse fromHashTag(HashTag hashTag){
        return new HashTagResponse(
                hashTag.getId(),
                hashTag.getUser(),
                hashTag.getTitle(),
                hashTag.getCount(),
                hashTag.getCreated_at(),
                hashTag.getCreated_by(),
                hashTag.getModified_at(),
                hashTag.getModified_by()
        );
    }


}
