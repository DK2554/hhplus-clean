package io.hhplus.cleanarchitecture.infra.lectureEnrollment;

import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaLectureEnrollmentRepository extends JpaRepository <LectureEnrollment, Long> {
    @Query("SELECT le FROM LectureEnrollment le " +
            "JOIN FETCH le.lecture l " +
            "JOIN FETCH le.instructor i " +
            "WHERE le.user.id = :userId")
    List<LectureEnrollment> findByUserId(@Param("userId") Long userId);
}
