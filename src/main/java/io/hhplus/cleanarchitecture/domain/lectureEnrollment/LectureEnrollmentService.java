package io.hhplus.cleanarchitecture.domain.lectureEnrollment;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureEnrollmentService {

    private final LectureEnrollmentRepository lectureEnrollmentRepository;

    public List<LectureEnrollment> findUserEnrolledLectures(Long userId) {
        return lectureEnrollmentRepository.findUserEnrolledLectures(userId);
    }
}
