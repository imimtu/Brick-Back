package com.im2.brickback.domain.entity;

import com.im2.brickback.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

/*
 할 일에 대한 기본적인 단위를 Brick 이라 지칭한다.
 (할일을 벽돌로 만들어 쌓는다는 의미)
 */

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "created_at"),
        @Index(columnList = "created_by")
}, name = "brick")
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
public class BrickEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false, length = 10000) private String content;
    @Setter private int priority;
    @Setter private LocalDateTime deadline;
    @Setter private String hashtag;
    @Setter private boolean is_completed;

    @CreatedDate @Column(nullable = false, length = 100) private LocalDateTime created_at;
    @CreatedBy @Column(nullable = false, length = 100) private String created_by;
    @LastModifiedDate @Column(nullable = false, length = 100) private LocalDateTime modified_at;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modified_by;

    public static BrickEntity of(UserEntity user, String title, String content, int priority, LocalDateTime deadline, String hashtag, boolean is_completed) {
        BrickEntity brickEntity = new BrickEntity();
        brickEntity.setUser(user);
        brickEntity.setTitle(title);
        brickEntity.setContent(content);
        brickEntity.setPriority(priority);
        brickEntity.setDeadline(deadline);
        brickEntity.setHashtag(hashtag);
        brickEntity.set_completed(is_completed);
        return brickEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrickEntity brick)) return false; // TODO: pattern variable !
        return id != null && id.equals(brick.id); // TODO: 영속화를 하지 않았을(연결짓지 않은) 경우 id 가 nullable 하므로 그런 경우는 != 처리 해준다.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
