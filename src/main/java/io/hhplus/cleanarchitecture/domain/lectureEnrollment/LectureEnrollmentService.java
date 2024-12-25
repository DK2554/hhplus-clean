package io.hhplus.cleanarchitecture.domain.lectureEnrollment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureEnrollmentService {

    private final LectureEnrollmentRepository lectureEnrollmentRepository;

    public List<LectureEnrollment> findUserEnrolledLectures(Long userId) {
        return lectureEnrollmentRepository.findUserEnrolledLectures(userId);
    }

    public void enrollInLecture(LectureEnrollment lectureEnrollment) {
         lectureEnrollmentRepository.save(lectureEnrollment);
    }
}
