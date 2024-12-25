package io.hhplus.cleanarchitecture.infra;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JpaLectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByApplicationStartAtBetween(LocalDate startDate, LocalDate endDate);
}
