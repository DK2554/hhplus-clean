package io.hhplus.cleanarchitecture.facade;

import io.hhplus.cleanarchitecture.domain.Instructor.Instructor;
import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.interfaces.lectureEnrollment.LectureEnrollmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserLectureEnrollmentFacade {

    private final LectureEnrollmentService lectureEnrollmentService;

    public List<LectureEnrollment> findUserEnrolledLectures(Long userId) {
        return lectureEnrollmentService.findUserEnrolledLectures(userId);
    }

    public void enrollInLecture(LectureEnrollmentRequest lectureEnrollmentRequest) {
        LectureEnrollment enrollment = toDomainModel(lectureEnrollmentRequest);
        lectureEnrollmentService.enrollInLecture(enrollment);
    }

    private LectureEnrollment toDomainModel(LectureEnrollmentRequest request) {
        return LectureEnrollment.builder()
                .user(User.builder().id(request.getUserId()).build())
                .lecture(Lecture.builder().id(request.getLectureId()).build())
                .instructor(Instructor.builder().id(request.getInstructorId()).build())
                .enrollmentDate(LocalDate.now())
                .build();
    }
}
