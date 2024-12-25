package io.hhplus.cleanarchitecture.domain.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<Lecture> findLecturesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return lectureRepository.findLecturesBetweenDates(startDate, endDate);
    }
}
