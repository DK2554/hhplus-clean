package io.hhplus.cleanarchitecture.domain.lecture;


import java.time.LocalDate;
import java.util.List;

public interface LectureRepository {
    List<Lecture> findLecturesBetweenDates(LocalDate startDate, LocalDate endDate);
}
