package com.im2.brickback.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Brick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private UserAccount userAccount;
    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false, length = 10000) private String content;
    @Setter private int priority;
    @Setter private LocalDateTime deadline;
    @Setter private String hashtag;
    @Setter private boolean is_completed;

    @CreatedDate @Column(nullable = false, length = 100) private LocalDateTime created_at;
    @CreatedBy @Column(nullable = false, length = 100) private String created_by; // 누가 했는지는 JpaConfig 에 임시로 달아놓음
    @LastModifiedDate @Column(nullable = false, length = 100) private LocalDateTime modified_at;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modified_by;

    protected Brick() {}

    public Brick(String title, String content, int priority, LocalDateTime deadline, String hashtag, boolean is_completed) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.deadline = deadline;
        this.hashtag = hashtag;
        this.is_completed = is_completed;
    }

    public static Brick of(String title, String content, int priority, LocalDateTime deadline, String hashtag, boolean is_completed) {
        return new Brick( title, content, priority, deadline, hashtag, is_completed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brick brick)) return false; // TODO: pattern variable !
        return id != null && id.equals(brick.id); // TODO: 영속화를 하지 않았을(연결짓지 않은) 경우 id 가 nullable 하므로 그런 경우는 != 처리 해준다.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
