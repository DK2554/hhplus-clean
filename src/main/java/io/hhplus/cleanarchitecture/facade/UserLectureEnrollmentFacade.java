package io.hhplus.cleanarchitecture.facade;

import io.hhplus.cleanarchitecture.HangHeaException;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
