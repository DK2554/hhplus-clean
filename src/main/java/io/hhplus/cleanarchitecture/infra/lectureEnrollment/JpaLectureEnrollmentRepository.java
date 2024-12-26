package io.hhplus.cleanarchitecture.infra.lectureEnrollment;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaLectureEnrollmentRepository extends JpaRepository <LectureEnrollment, Long> {
    @Query("SELECT le FROM LectureEnrollment le " +
            "JOIN FETCH le.lecture l " +
            "JOIN FETCH le.instructor i " +
            "WHERE le.user.id = :userId")
    List<LectureEnrollment> findByUserId(@Param("userId") Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적 락을 사용하여 데이터 수정 중 다른 트랜잭션에서 접근을 막음
    @Query("SELECT l FROM Lecture l WHERE l.id = :id")
    Optional<Lecture> findByIdWithLock(@Param("id") Long id);

    Optional<LectureEnrollment> findByUserIdAndLectureId(Long userId, Long lectureId);
}
