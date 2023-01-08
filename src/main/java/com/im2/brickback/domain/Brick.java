package com.im2.brickback.domain;

import java.time.LocalDateTime;

/*
 할 일에 대한 기본적인 단위를 Brick 이라 지칭한다.
 (할일을 벽돌로 만들어 쌓는다는 의미)
 */
public class Brick {
    private Long id;
    private UserAccount userAccount;
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
}
