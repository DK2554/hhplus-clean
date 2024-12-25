package io.hhplus.cleanarchitecture.interfaces.user;

import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.facade.UserLectureEnrollmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserLectureEnrollmentController {

    private final UserLectureEnrollmentFacade userLectureEnrollmentFacade;

    //사용자의 특상 완료 신청 목록
    @GetMapping("/lectures/enrollments/user/{id}")
    public ResponseEntity<List<LectureEnrollment>> findUserEnrolledLectures(@PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(userLectureEnrollmentFacade.findUserEnrolledLectures(userId));
    }
}
