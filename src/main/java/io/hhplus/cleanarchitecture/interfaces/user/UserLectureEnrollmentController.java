package io.hhplus.cleanarchitecture.interfaces.user;

import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.facade.UserLectureEnrollmentFacade;
import io.hhplus.cleanarchitecture.interfaces.lectureEnrollment.LectureEnrollmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserLectureEnrollmentController {

    private final UserLectureEnrollmentFacade userLectureEnrollmentFacade;

    //사용자의 특강 완료 신청 목록
    @GetMapping("/lectures/enrollments/user/{id}")
    public ResponseEntity<List<LectureEnrollment>> findUserEnrolledLectures(@PathVariable(name = "id") Long userId) {
        UserLectureEnrollmentFinder request = new UserLectureEnrollmentFinder(userId);//생성자를 사용해 직접반환 생성자에 유효성 검증 로직적용
        return ResponseEntity.ok(userLectureEnrollmentFacade.findUserEnrolledLectures(request.getUserId()));
    }

    //특강신청청
    @PostMapping("/lectures/enrollment")
    public ResponseEntity<List<LectureEnrollment>> enrollInLecture(@RequestBody LectureEnrollmentRequest lectureEnrollmentRequest) {
        userLectureEnrollmentFacade.enrollInLecture(lectureEnrollmentRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
