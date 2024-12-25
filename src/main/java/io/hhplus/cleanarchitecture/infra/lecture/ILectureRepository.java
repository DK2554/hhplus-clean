package io.hhplus.cleanarchitecture.infra.lecture;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ILectureRepository implements LectureRepository {

    private final JpaLectureRepository jpaLectureRepository;

    @Override
    public List<Lecture> findLecturesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return jpaLectureRepository.findByApplicationStartAtBetween(startDate, endDate);
    }

}
