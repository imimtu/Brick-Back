package com.im2.brickback.controller.request;

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
    private LocalDateTime deadline;
    private String hashtag;
}
