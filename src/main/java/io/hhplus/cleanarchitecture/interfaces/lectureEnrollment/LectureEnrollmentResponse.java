package io.hhplus.cleanarchitecture.interfaces.lectureEnrollment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LectureEnrollmentResponse {
    private String lectureName; // 강의 이름
    private String userName;    // 사용자 이름
    private LocalDateTime enrollmentDate; // 신청 날짜
    private String status;      // 신청 상태
}