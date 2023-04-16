package com.im2.brickback.controller.request.brick;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BrickModifyRequest {
    private String title;
    private String content;
    private int priority;
    private String deadline;
    private String hashtag;
}
