package io.hhplus.cleanarchitecture.domain.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"user\"") // 예약어를 이스케이프
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    @Column(name = "user_name", nullable = false)
    private String userName; // 사용자 이름

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt; // 생성 시간

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // 수정 시간

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isEmpty() {
        return id == null || id == 0; // 예시: id가 없거나 0이면 빈 객체로 간주
    }
}
