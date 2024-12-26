package io.hhplus.cleanarchitecture.facade;

import io.hhplus.cleanarchitecture.HangHeaException;
import io.hhplus.cleanarchitecture.domain.Instructor.Instructor;
import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.domain.user.UserService;
import io.hhplus.cleanarchitecture.interfaces.lectureEnrollment.LectureEnrollmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserLectureEnrollmentFacade {

    private final LectureEnrollmentService lectureEnrollmentService;
    private final UserService userService;

    public List<LectureEnrollment> findUserEnrolledLectures(Long userId) {

        User user = userService.findByUserId(userId);

        if (user.isEmpty()) {
            throw new HangHeaException("존재하지 않는 사용자입니다.");
        }

        return lectureEnrollmentService.findUserEnrolledLectures(userId);
    }

    @Transactional
    public void enrollInLecture(LectureEnrollmentRequest lectureEnrollmentRequest) {
        LectureEnrollment enrollment = toDomainModel(lectureEnrollmentRequest);

        User user = userService.findByUserId(enrollment.getUser().getId());

        // 사용자 존재 여부 확인
        if (user.isEmpty()) {
            throw new HangHeaException("존재하지 않는 사용자입니다.");
        }

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
