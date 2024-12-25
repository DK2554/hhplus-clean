package io.hhplus.cleanarchitecture.interfaces.lectureEnrollment;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lecture.LectureService;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
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
public class LectureEnrollmentController {

    private final LectureEnrollmentService lectureEnrollmentService;

    //사용자의 특상 완료 신청 목록
    @GetMapping("/lecture/enrollment/user/{id}")
    public ResponseEntity<List<LectureEnrollment>> findUserEnrolledLectures(@PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(lectureEnrollmentService.findUserEnrolledLectures(userId));
    }
}
