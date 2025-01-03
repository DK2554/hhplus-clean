package io.hhplus.cleanarchitecture.interfaces.lecture;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lecture.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    // 특정 기간의 특강 목록 조회
    @GetMapping("/lectures")
    public ResponseEntity<List<Lecture>> findLecturesBetweenDates(@ModelAttribute LectureDateRangeRequest lectureRequest) {
        List<Lecture> lectures = lectureService.findLecturesBetweenDates(lectureRequest.getStartDate(), lectureRequest.getEndDate());
        return ResponseEntity.ok(lectures);
    }

}
