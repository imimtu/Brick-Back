package com.im2.brickback.controller.request.brick;

import com.im2.brickback.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BrickCreateRequest {
    private String title;
    private String content;
    private int priority;
    private String deadline;
    private String hashtag;
}
