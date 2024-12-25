package io.hhplus.cleanarchitecture.facade;

import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserLectureEnrollmentFacade {

    private final LectureEnrollmentService lectureEnrollmentService;

    public List<LectureEnrollment> findUserEnrolledLectures(Long userId) {
        return lectureEnrollmentService.findUserEnrolledLectures(userId);
    }
}
