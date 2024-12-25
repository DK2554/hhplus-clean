package io.hhplus.cleanarchitecture.domain.lecture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    @Column(name = "lecture_name", nullable = false)
    private String lectureName; // 강의 이름

    @Column(name = "max_capacity", nullable = false)
    private Long maxCapacity; // 최대 수강 가능 인원

    @Column(name = "current_capacity", nullable = false)
    private Long currentCapacity; // 현재 수강 인원

    @Column(name = "application_start_at", nullable = false)
    private LocalDate applicationStartAt; // 수강 신청 시작일

    @Column(name = "application_end_at", nullable = false)
    private LocalDate applicationEndAt; // 수강 신청 종료일

    @Column(name = "lecture_start", nullable = false)
    private LocalDateTime lectureStart; // 강의 시작일

    @Column(name = "lecture_end", nullable = false)
    private LocalDateTime lectureEnd; // 강의 종료일

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
