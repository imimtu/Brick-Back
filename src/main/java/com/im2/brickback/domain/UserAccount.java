package com.im2.brickback.domain;

import java.time.LocalDateTime;

public class UserAccount {
    private Long id;
    private String user_id;
    private String user_password;
    private String nickname;
    private String email;
    private String phone_number;
    private boolean is_active;
    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime modified_at;
    private String modified_by;
}
