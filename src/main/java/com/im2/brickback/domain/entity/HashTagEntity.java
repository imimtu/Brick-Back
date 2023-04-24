package com.im2.brickback.domain.entity;

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

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "user_id"),
        @Index(columnList = "created_at"),
        @Index(columnList = "created_by")
}, name = "hashtag")
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
public class HashTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Setter @Column(nullable = false) private String title;
    @Setter private int count;

    @CreatedDate @Column(nullable = false, length = 100) private LocalDateTime created_at;
    @CreatedBy @Column(nullable = false, length = 100) private String created_by;
    @LastModifiedDate @Column(nullable = false, length = 100) private LocalDateTime modified_at;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modified_by;

    public static HashTagEntity of(UserEntity user, String title, int count){
        HashTagEntity hashTagEntity = new HashTagEntity();
        hashTagEntity.setUser(user);
        hashTagEntity.setTitle(title);
        hashTagEntity.setCount(count);
        return hashTagEntity;
    }

}
